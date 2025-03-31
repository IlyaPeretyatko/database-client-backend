package ru.nsu.peretyatko.service.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.weapons.WeaponCategoryMapper;
import ru.nsu.peretyatko.model.weapons.WeaponCategory;
import ru.nsu.peretyatko.repository.weapons.WeaponCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeaponCategoryService {

    private final WeaponCategoryRepository weaponCategoryRepository;

    private final WeaponCategoryMapper weaponCategoryMapper;

    @Transactional(readOnly = true)
    public List<WeaponCategoryResponse> getWeaponCategories() {
        return weaponCategoryRepository.findAll().stream().map(weaponCategoryMapper::toWeaponCategoryResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WeaponCategoryResponse getWeaponCategory(int id) {
        WeaponCategory weaponCategory = weaponCategoryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon category was not found."));
        return weaponCategoryMapper.toWeaponCategoryResponse(weaponCategory);
    }

    @Transactional
    public void createWeaponCategory(WeaponCategoryRequest weaponCategoryRequest) {
        weaponCategoryRepository.save(weaponCategoryMapper.toWeaponCategory(weaponCategoryRequest));
    }

    @Transactional
    public void deleteWeaponCategory(int id) {
        if (!weaponCategoryRepository.existsById(id)) { throw new ServiceException(404, "Weapon category was not found."); }
        weaponCategoryRepository.deleteById(id);
    }
}