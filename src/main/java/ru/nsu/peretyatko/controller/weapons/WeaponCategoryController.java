package ru.nsu.peretyatko.controller.weapons;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryResponse;
import ru.nsu.peretyatko.service.weapons.WeaponCategoryService;
import ru.nsu.peretyatko.validator.weapons.WeaponCategoryValidator;

import java.util.List;

@Tag(name = "Weapon API")
@RestController
@RequestMapping("/weapons/categories")
@RequiredArgsConstructor
public class WeaponCategoryController {

    private final WeaponCategoryService weaponCategoryService;

    private final WeaponCategoryValidator weaponCategoryValidator;

    @Operation(summary = "Получить перечень категорий оружия")
    @GetMapping
    public Page<WeaponCategoryResponse> getWeaponCategories(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return weaponCategoryService.getWeaponCategories(page, size);
    }

    @Operation(summary = "Получить категорию оружия по ID")
    @GetMapping("/{id}")
    public WeaponCategoryResponse getWeaponCategoryById(@PathVariable int id) {
        return weaponCategoryService.getWeaponCategory(id);
    }

    @Operation(summary = "Добавить категорию оружия")
    @PostMapping
    public void createWeaponCategory(@Valid @RequestBody WeaponCategoryRequest weaponCategoryRequest,
                                        BindingResult bindingResult) {
        weaponCategoryValidator.validate(weaponCategoryRequest, bindingResult);
        weaponCategoryService.createWeaponCategory(weaponCategoryRequest);
    }

    @Operation(summary = "Удалить категорию оружия по ID")
    @DeleteMapping("/{id}")
    public void deleteWeaponCategory(@PathVariable int id) {
        weaponCategoryService.deleteWeaponCategory(id);
    }
}