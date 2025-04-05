package ru.nsu.peretyatko.controller.weapons;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyResponse;
import ru.nsu.peretyatko.service.weapons.WeaponPropertyService;
import ru.nsu.peretyatko.validator.weapons.WeaponPropertyValidator;

import java.util.List;

@RestController
@RequestMapping("/api/weapons/properties")
@RequiredArgsConstructor
public class WeaponPropertyController {

    private final WeaponPropertyService weaponPropertyService;

    private final WeaponPropertyValidator weaponPropertyValidator;

    @GetMapping
    public List<WeaponPropertyResponse> getWeaponProperties() {
        return weaponPropertyService.getWeaponProperties();
    }

    @GetMapping("/{id}")
    public WeaponPropertyResponse getWeaponPropertyById(@PathVariable int id) {
        return weaponPropertyService.getWeaponProperty(id);
    }

    @PostMapping
    public void createWeaponProperty(@Valid @RequestBody WeaponPropertyPostRequest weaponPropertyPostRequest,
                                        BindingResult bindingResult) {
        weaponPropertyValidator.validate(weaponPropertyPostRequest, bindingResult);
        weaponPropertyService.createWeaponProperty(weaponPropertyPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateWeaponProperty(@PathVariable int id,
                                        @Valid @RequestBody WeaponPropertyPatchRequest weaponPropertyPatchRequest,
                                        BindingResult bindingResult) {
        weaponPropertyValidator.validate(weaponPropertyPatchRequest, bindingResult);
        weaponPropertyService.updateWeaponProperty(id, weaponPropertyPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWeaponProperty(@PathVariable int id) {
        weaponPropertyService.deleteWeaponProperty(id);
    }
}