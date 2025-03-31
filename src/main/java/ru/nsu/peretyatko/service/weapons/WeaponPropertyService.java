package ru.nsu.peretyatko.service.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.weapons.WeaponPropertyMapper;
import ru.nsu.peretyatko.model.weapons.WeaponProperty;
import ru.nsu.peretyatko.repository.weapons.WeaponPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeaponPropertyService {
    private final WeaponPropertyRepository weaponPropertyRepository;

    private final WeaponPropertyMapper weaponPropertyMapper;

    @Transactional(readOnly = true)
    public List<WeaponPropertyResponse> getWeaponProperties() {
        return weaponPropertyRepository.findAll().stream().map(weaponPropertyMapper::toWeaponPropertyResponse).toList();
    }

    @Transactional(readOnly = true)
    public WeaponPropertyResponse getWeaponProperty(int id) {
        WeaponProperty weaponProperty = weaponPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon property was not found."));
        return weaponPropertyMapper.toWeaponPropertyResponse(weaponProperty);
    }

    @Transactional
    public void createWeaponProperty(WeaponPropertyPostRequest weaponPropertyPostRequest) {
        weaponPropertyRepository.save(weaponPropertyMapper.toWeaponProperty(weaponPropertyPostRequest));
    }

    @Transactional
    public void updateWeaponProperty(int id, WeaponPropertyPatchRequest weaponPropertyPatchRequest) {
        WeaponProperty weaponProperty = weaponPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon property was not found."));
        weaponPropertyMapper.updateWeaponProperty(weaponProperty, weaponPropertyPatchRequest);
        weaponPropertyRepository.save(weaponProperty);
    }

    @Transactional
    public void deleteWeaponProperty(int id) {
        if (!weaponPropertyRepository.existsById(id)) {
            throw new ServiceException(404, "Weapon property was not found.");
        }
        weaponPropertyRepository.deleteById(id);
    }
}