package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.UnitMapper;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.repository.infrastructure.UnitCustomRepository;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitCustomRepository unitCustomRepository;

    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnits(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitRepository.findAll(pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public UnitResponse getUnit(int id) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
        return unitMapper.toUnitResponse(unit);
    }

    @Transactional
    public void createUnit(UnitPostRequest unitPostRequest) {
        unitRepository.save(unitMapper.toUnit(unitPostRequest));
    }

    @Transactional
    public void updateUnit(int id, UnitPatchRequest unitPatchRequest) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
        unitMapper.updateUnit(unit, unitPatchRequest);
        unitRepository.save(unit);
    }

    @Transactional
    public void deleteUnit(int id) {
        if (!unitRepository.existsById(id)) {
            throw new ServiceException(404, "Unit was not found.");
        }
        unitRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsByDivision(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsByDivisionId(id, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsByBrigade(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsByBrigadeId(id, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsByCorps(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsByCorpsId(id, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsByArmy(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsByArmyId(id, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsWithEquipmentTypeCount(String titleType, int minCount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsWithEquipmentTypeCount(titleType, minCount, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsWithoutEquipmentType(String titleType, int page, int size)  {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsWithoutEquipmentType(titleType, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsWithWeaponTypeCount(String titleType, int minCount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsWithWeaponTypeCount(titleType, minCount, pageable).map(unitMapper::toUnitResponse);
    }

    @Transactional(readOnly = true)
    public Page<UnitResponse> getUnitsWithoutWeaponType(String titleType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unitCustomRepository.findUnitsWithoutWeaponType(titleType, pageable).map(unitMapper::toUnitResponse);
    }
}