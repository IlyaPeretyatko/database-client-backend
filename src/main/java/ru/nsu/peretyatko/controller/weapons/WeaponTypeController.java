package ru.nsu.peretyatko.controller.weapons;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeResponse;
import ru.nsu.peretyatko.service.weapons.WeaponTypeService;
import ru.nsu.peretyatko.validator.weapons.WeaponTypeValidator;

import java.util.List;

@Tag(name = "Weapon API")
@RestController
@RequestMapping("/weapons/types")
@RequiredArgsConstructor
public class WeaponTypeController {

    private final WeaponTypeService weaponTypeService;

    private final WeaponTypeValidator weaponTypeValidator;

    @Operation(summary = "Получить перечень видов оружия")
    @GetMapping
    public List<WeaponTypeResponse> getWeaponTypes() {
        return weaponTypeService.getWeaponTypes();
    }

    @Operation(summary = "Получить вид оружия по ID")
    @GetMapping("/{id}")
    public WeaponTypeResponse getWeaponTypeById(@PathVariable int id) {
        return weaponTypeService.getWeaponType(id);
    }

    @Operation(summary = "Добавить вид оружия")
    @PostMapping
    public void createWeaponType(@Valid @RequestBody WeaponTypeRequest weaponTypeRequest,
                                    BindingResult bindingResult) {
        weaponTypeValidator.validate(weaponTypeRequest, bindingResult);
        weaponTypeService.createWeaponType(weaponTypeRequest);
    }

    @Operation(summary = "Удалить вид оружия по ID")
    @DeleteMapping("/{id}")
    public void deleteWeaponType(@PathVariable int id) {
        weaponTypeService.deleteWeaponType(id);
    }
}
