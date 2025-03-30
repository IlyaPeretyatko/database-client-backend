package ru.nsu.peretyatko.mapper.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.weapons.Weapon;
import ru.nsu.peretyatko.model.weapons.WeaponType;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;
import ru.nsu.peretyatko.repository.weapons.WeaponTypeRepository;

@Component
@RequiredArgsConstructor
public class WeaponMapper {

    private final WeaponTypeRepository weaponTypeRepository;

    private final UnitRepository unitRepository;

    public Weapon toWeapon(WeaponPostRequest weaponPostRequest) {
        Weapon weapon = new Weapon();
        weapon.setSerialNumber(weaponPostRequest.getSerialNumber());
        WeaponType weaponType = weaponTypeRepository.findById(weaponPostRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Weapon type was not found."));
        weapon.setType(weaponType);
        Unit unit = unitRepository.findById(weaponPostRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
        weapon.setUnit(unit);
        return weapon;
    }

    public WeaponResponse toWeaponResponse(Weapon weapon) {
        WeaponResponse weaponResponse = new WeaponResponse();
        weaponResponse.setId(weapon.getId());
        weaponResponse.setSerialNumber(weapon.getSerialNumber());
        weaponResponse.setTypeId(weapon.getType().getId());
        weaponResponse.setUnitId(weapon.getUnit().getId());
        return weaponResponse;
    }

    public void updateWeapon(Weapon weapon, WeaponPatchRequest weaponPatchRequest) {
        if (weaponPatchRequest.getSerialNumber() != null) {
            weapon.setSerialNumber(weaponPatchRequest.getSerialNumber());
        }
        if (weaponPatchRequest.getTypeId() != null) {
            WeaponType weaponType = weaponTypeRepository.findById(weaponPatchRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Weapon type was not found."));
            weapon.setType(weaponType);
        }
        if (weaponPatchRequest.getUnitId() != null) {
            Unit unit = unitRepository.findById(weaponPatchRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
            weapon.setUnit(unit);
        }
    }
}
