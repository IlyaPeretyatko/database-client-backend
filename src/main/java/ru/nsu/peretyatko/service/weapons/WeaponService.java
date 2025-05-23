package ru.nsu.peretyatko.service.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.weapons.WeaponMapper;
import ru.nsu.peretyatko.model.weapons.Weapon;
import ru.nsu.peretyatko.repository.weapons.WeaponCustomRepository;
import ru.nsu.peretyatko.repository.weapons.WeaponRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeaponService {

    private final WeaponCustomRepository weaponCustomRepository;

    private final WeaponRepository weaponRepository;

    private final WeaponMapper weaponMapper;

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeapons(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponRepository.findAll(pageable).map(weaponMapper::toWeaponResponse);
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

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByType(String titleType) {
        return weaponCustomRepository.findWeaponsByType(titleType).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByCategory(String titleCategory) {
        return weaponCustomRepository.findWeaponsByCategory(titleCategory).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByTypeUnit(String titleType, int unitId) {
        return weaponCustomRepository.findWeaponsByTypeUnit(titleType, unitId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByCategoryUnit(String titleCategory, int unitId) {
        return weaponCustomRepository.findWeaponsByCategoryUnit(titleCategory, unitId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByTypeDivision(String titleType, int divisionId) {
        return weaponCustomRepository.findWeaponsByTypeDivision(titleType, divisionId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByCategoryDivision(String titleCategory, int divisionId) {
        return weaponCustomRepository.findWeaponsByCategoryDivision(titleCategory, divisionId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByTypeBrigade(String titleType, int brigadeId) {
        return weaponCustomRepository.findWeaponsByTypeBrigade(titleType, brigadeId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByCategoryBrigade(String titleCategory, int brigadeId) {
        return weaponCustomRepository.findWeaponsByCategoryBrigade(titleCategory, brigadeId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByTypeCorps(String titleType, int corpsId) {
        return weaponCustomRepository.findWeaponsByTypeCorps(titleType, corpsId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByCategoryCorps(String titleCategory, int corpsId) {
        return weaponCustomRepository.findWeaponsByCategoryCorps(titleCategory, corpsId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByTypeArmy(String titleType, int armyId) {
        return weaponCustomRepository.findWeaponsByTypeArmy(titleType, armyId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<WeaponResponse> getWeaponsByCategoryArmy(String titleCategory, int armyId) {
        return weaponCustomRepository.findWeaponsByCategoryArmy(titleCategory, armyId).stream().map(weaponMapper::toWeaponResponse).toList();
    }

}