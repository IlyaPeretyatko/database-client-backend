package ru.nsu.peretyatko.service.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponTypeResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.weapons.WeaponTypeMapper;
import ru.nsu.peretyatko.model.weapons.WeaponType;
import ru.nsu.peretyatko.repository.weapons.WeaponTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeaponTypeService {

    private final WeaponTypeRepository weaponTypeRepository;

    private final WeaponTypeMapper weaponTypeMapper;

    @Transactional(readOnly = true)
    public Page<WeaponTypeResponse> getWeaponTypes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return weaponTypeRepository.findAll(pageable).map(weaponTypeMapper::toWeaponTypeResponse);
    }

    @Transactional(readOnly = true)
    public WeaponTypeResponse getWeaponType(int id) {
        WeaponType weaponType = weaponTypeRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon type was not found."));
        return weaponTypeMapper.toWeaponTypeResponse(weaponType);
    }

    @Transactional
    public void createWeaponType(WeaponTypeRequest weaponTypeRequest) {
        weaponTypeRepository.save(weaponTypeMapper.toWeaponType(weaponTypeRequest));
    }

    @Transactional
    public void deleteWeaponType(int id) {
        if (!weaponTypeRepository.existsById(id)) { throw new ServiceException(404, "Weapon type was not found."); }
        weaponTypeRepository.deleteById(id);
    }
}