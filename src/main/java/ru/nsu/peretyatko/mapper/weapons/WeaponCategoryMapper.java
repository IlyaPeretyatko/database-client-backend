package ru.nsu.peretyatko.mapper.weapons;

import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryResponse;
import ru.nsu.peretyatko.model.weapons.WeaponCategory;

@Component
public class WeaponCategoryMapper {
    public WeaponCategory toWeaponCategory(WeaponCategoryRequest weaponCategoryRequest) {
        WeaponCategory weaponCategory = new WeaponCategory();
        weaponCategory.setTitle(weaponCategoryRequest.getTitle());
        return weaponCategory;
    }

    public WeaponCategoryResponse toWeaponCategoryResponse(WeaponCategory weaponCategory) {
        WeaponCategoryResponse weaponCategoryResponse = new WeaponCategoryResponse();
        weaponCategoryResponse.setId(weaponCategory.getId());
        weaponCategoryResponse.setTitle(weaponCategory.getTitle());
        return weaponCategoryResponse;
    }
}
