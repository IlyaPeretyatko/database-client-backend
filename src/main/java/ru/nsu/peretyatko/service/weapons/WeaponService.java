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
    public Page<WeaponResponse> getWeaponsByType(String titleType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByType(titleType, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByCategory(String titleCategory, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByCategory(titleCategory, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByTypeUnit(String titleType, int unitId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByTypeUnit(titleType, unitId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByCategoryUnit(String titleCategory, int unitId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByCategoryUnit(titleCategory, unitId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByTypeDivision(String titleType, int divisionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByTypeDivision(titleType, divisionId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByCategoryDivision(String titleCategory, int divisionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByCategoryDivision(titleCategory, divisionId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByTypeBrigade(String titleType, int brigadeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByTypeBrigade(titleType, brigadeId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByCategoryBrigade(String titleCategory, int brigadeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByCategoryBrigade(titleCategory, brigadeId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByTypeCorps(String titleType, int corpsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByTypeCorps(titleType, corpsId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByCategoryCorps(String titleCategory, int corpsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByCategoryCorps(titleCategory, corpsId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByTypeArmy(String titleType, int armyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByTypeArmy(titleType, armyId, pageable).map(weaponMapper::toWeaponResponse);
    }

    @Transactional(readOnly = true)
    public Page<WeaponResponse> getWeaponsByCategoryArmy(String titleCategory, int armyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponCustomRepository.findWeaponsByCategoryArmy(titleCategory, armyId, pageable).map(weaponMapper::toWeaponResponse);
    }

}