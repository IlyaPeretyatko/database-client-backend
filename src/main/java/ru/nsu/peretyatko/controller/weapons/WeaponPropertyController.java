package ru.nsu.peretyatko.controller.weapons;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Weapon API")
@RestController
@RequestMapping("/weapons/properties")
@RequiredArgsConstructor
public class WeaponPropertyController {

    private final WeaponPropertyService weaponPropertyService;

    private final WeaponPropertyValidator weaponPropertyValidator;

    @Operation(summary = "Получить перечень атрибутов оружия")
    @GetMapping
    public List<WeaponPropertyResponse> getWeaponProperties() {
        return weaponPropertyService.getWeaponProperties();
    }

    @Operation(summary = "Получить атрибут оружия по ID")
    @GetMapping("/{id}")
    public WeaponPropertyResponse getWeaponPropertyById(@PathVariable int id) {
        return weaponPropertyService.getWeaponProperty(id);
    }

    @Operation(summary = "Добавить атрибут оружия")
    @PostMapping
    public void createWeaponProperty(@Valid @RequestBody WeaponPropertyPostRequest weaponPropertyPostRequest,
                                        BindingResult bindingResult) {
        weaponPropertyValidator.validate(weaponPropertyPostRequest, bindingResult);
        weaponPropertyService.createWeaponProperty(weaponPropertyPostRequest);
    }

    @Operation(summary = "Изменить данные атрибута оружия по ID")
    @PatchMapping("/{id}")
    public void updateWeaponProperty(@PathVariable int id,
                                        @Valid @RequestBody WeaponPropertyPatchRequest weaponPropertyPatchRequest,
                                        BindingResult bindingResult) {
        weaponPropertyValidator.validate(weaponPropertyPatchRequest, bindingResult);
        weaponPropertyService.updateWeaponProperty(id, weaponPropertyPatchRequest);
    }

    @Operation(summary = "Удалить атрибут оружия по ID")
    @DeleteMapping("/{id}")
    public void deleteWeaponProperty(@PathVariable int id) {
        weaponPropertyService.deleteWeaponProperty(id);
    }
}