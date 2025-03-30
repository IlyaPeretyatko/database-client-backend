package ru.nsu.peretyatko.mapper.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.weapons.WeaponProperty;
import ru.nsu.peretyatko.model.weapons.WeaponType;
import ru.nsu.peretyatko.repository.weapons.WeaponTypeRepository;

@Component
@RequiredArgsConstructor
public class WeaponPropertyMapper {

    private final WeaponTypeRepository weaponTypeRepository;

    public WeaponProperty toWeaponProperty(WeaponPropertyPostRequest weaponPropertyPostRequest) {
        WeaponProperty weaponProperty = new WeaponProperty();
        weaponProperty.setTitle(weaponPropertyPostRequest.getTitle());
        weaponProperty.setValue(weaponPropertyPostRequest.getValue());
        WeaponType weaponType = weaponTypeRepository.findById(weaponPropertyPostRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Weapon type was not found."));
        weaponProperty.setType(weaponType);
        return weaponProperty;
    }

    public WeaponPropertyResponse toWeaponPropertyResponse(WeaponProperty weaponProperty) {
        WeaponPropertyResponse weaponPropertyResponse = new WeaponPropertyResponse();
        weaponPropertyResponse.setId(weaponProperty.getId());
        weaponPropertyResponse.setTitle(weaponProperty.getTitle());
        weaponPropertyResponse.setValue(weaponProperty.getValue());
        weaponPropertyResponse.setTypeId(weaponProperty.getType().getId());
        return weaponPropertyResponse;
    }

    public void updateWeaponProperty(WeaponProperty weaponProperty, WeaponPropertyPatchRequest weaponPropertyPatchRequest) {
        if (weaponPropertyPatchRequest.getTitle() != null) {
            weaponProperty.setTitle(weaponPropertyPatchRequest.getTitle());
        }
        if (weaponPropertyPatchRequest.getValue() != null) {
            weaponProperty.setValue(weaponPropertyPatchRequest.getValue());
        }
        if (weaponPropertyPatchRequest.getTypeId() != null) {
            WeaponType weaponType = weaponTypeRepository.findById(weaponPropertyPatchRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Weapon type was not found."));
            weaponProperty.setType(weaponType);
        }
    }
}
