package ru.nsu.peretyatko.controller.weapons;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryResponse;
import ru.nsu.peretyatko.service.weapons.WeaponCategoryService;
import ru.nsu.peretyatko.validator.weapons.WeaponCategoryValidator;

import java.util.List;

@RestController
@RequestMapping("/weapons/categories")
@RequiredArgsConstructor
public class WeaponCategoryController {

    private final WeaponCategoryService weaponCategoryService;

    private final WeaponCategoryValidator weaponCategoryValidator;

    @GetMapping
    public List<WeaponCategoryResponse> getWeaponCategories() {
        return weaponCategoryService.getWeaponCategories();
    }

    @GetMapping("/{id}")
    public WeaponCategoryResponse getWeaponCategoryById(@PathVariable int id) {
        return weaponCategoryService.getWeaponCategory(id);
    }

    @PostMapping
    public void createWeaponCategory(@Valid @RequestBody WeaponCategoryRequest weaponCategoryRequest,
                                        BindingResult bindingResult) {
        weaponCategoryValidator.validate(weaponCategoryRequest, bindingResult);
        weaponCategoryService.createWeaponCategory(weaponCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWeaponCategory(@PathVariable int id) {
        weaponCategoryService.deleteWeaponCategory(id);
    }
}