package ru.nsu.peretyatko.mapper.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.weapons.WeaponCategory;
import ru.nsu.peretyatko.model.weapons.WeaponType;
import ru.nsu.peretyatko.repository.weapons.WeaponCategoryRepository;

@Component
@RequiredArgsConstructor
public class WeaponTypeMapper {

    private final WeaponCategoryRepository weaponCategoryRepository;

    public WeaponType toWeaponType(WeaponTypeRequest weaponTypeRequest) {
        WeaponType weaponType = new WeaponType();
        weaponType.setTitle(weaponTypeRequest.getTitle());
        WeaponCategory weaponCategory = weaponCategoryRepository.findById(weaponTypeRequest.getCategoryId()).orElseThrow(() -> new ServiceException(404, "Category was not found."));
        weaponType.setCategory(weaponCategory);
        return weaponType;
    }

    public WeaponTypeResponse toWeaponTypeResponse(WeaponType weaponType) {
        WeaponTypeResponse weaponTypeResponse = new WeaponTypeResponse();
        weaponTypeResponse.setId(weaponType.getId());
        weaponTypeResponse.setTitle(weaponType.getTitle());
        weaponTypeResponse.setCategoryId(weaponType.getCategory().getId());
        return weaponTypeResponse;
    }
}
