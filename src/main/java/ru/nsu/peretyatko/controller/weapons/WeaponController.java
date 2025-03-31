package ru.nsu.peretyatko.controller.weapons;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;
import ru.nsu.peretyatko.service.weapons.WeaponService;
import ru.nsu.peretyatko.validator.weapons.WeaponValidator;

import java.util.List;

@RestController
@RequestMapping("/api/weapon")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;

    private final WeaponValidator weaponValidator;

    @GetMapping
    public List<WeaponResponse> getWeapons() {
        return weaponService.getWeapons();
    }

    @GetMapping("/{id}")
    public WeaponResponse getWeaponById(@PathVariable int id) {
        return weaponService.getWeapon(id);
    }

    @PostMapping
    public void createWeapon(@Valid @RequestBody WeaponPostRequest weaponPostRequest,
                                BindingResult bindingResult) {
        weaponValidator.validate(weaponPostRequest, bindingResult);
        weaponService.createWeapon(weaponPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateWeapon(@PathVariable int id,
                                @Valid @RequestBody WeaponPatchRequest weaponPatchRequest,
                                BindingResult bindingResult) {
        weaponValidator.validate(weaponPatchRequest, bindingResult);
        weaponService.updateWeapon(id, weaponPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWeapon(@PathVariable int id) {
        weaponService.deleteWeapon(id);
    }
}