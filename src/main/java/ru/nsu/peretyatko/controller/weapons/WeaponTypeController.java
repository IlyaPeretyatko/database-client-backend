package ru.nsu.peretyatko.controller.weapons;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeResponse;
import ru.nsu.peretyatko.service.weapons.WeaponTypeService;
import ru.nsu.peretyatko.validator.weapons.WeaponTypeValidator;

import java.util.List;

@RestController
@RequestMapping("/weapons/types")
@RequiredArgsConstructor
public class WeaponTypeController {

    private final WeaponTypeService weaponTypeService;

    private final WeaponTypeValidator weaponTypeValidator;

    @GetMapping
    public List<WeaponTypeResponse> getWeaponTypes() {
        return weaponTypeService.getWeaponTypes();
    }

    @GetMapping("/{id}")
    public WeaponTypeResponse getWeaponTypeById(@PathVariable int id) {
        return weaponTypeService.getWeaponType(id);
    }

    @PostMapping
    public void createWeaponType(@Valid @RequestBody WeaponTypeRequest weaponTypeRequest,
                                    BindingResult bindingResult) {
        weaponTypeValidator.validate(weaponTypeRequest, bindingResult);
        weaponTypeService.createWeaponType(weaponTypeRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWeaponType(@PathVariable int id) {
        weaponTypeService.deleteWeaponType(id);
    }
}
