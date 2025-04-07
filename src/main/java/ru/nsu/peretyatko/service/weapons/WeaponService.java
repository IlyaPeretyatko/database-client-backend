package ru.nsu.peretyatko.service.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.weapons.WeaponMapper;
import ru.nsu.peretyatko.model.weapons.Weapon;
import ru.nsu.peretyatko.repository.weapons.WeaponRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    private final WeaponMapper weaponMapper;

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeapons() {
        return weaponRepository.findAll().stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public WeaponResponse getWeapon(int id) {
        Weapon weapon = weaponRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon was not found."));
        return weaponMapper.toWeaponResponse(weapon);
    }

    @Transactional
    public void createWeapon(WeaponPostRequest weaponPostRequest) {
        weaponRepository.save(weaponMapper.toWeapon(weaponPostRequest));
    }

    @Transactional
    public void updateWeapon(int id, WeaponPatchRequest weaponPatchRequest) {
        Weapon weapon = weaponRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon was not found."));
        weaponMapper.updateWeapon(weapon, weaponPatchRequest);
        weaponRepository.save(weapon);
    }

    @Transactional
    public void deleteWeapon(int id) {
        if (!weaponRepository.existsById(id)) {
            throw new ServiceException(404, "Weapon was not found.");
        }
        weaponRepository.deleteById(id);
    }
}