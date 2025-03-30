package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.UnitMapper;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    @Transactional(readOnly = true)
    public List<UnitResponse> getUnits() {
        return unitRepository.findAll().stream().map(unitMapper::toUnitResponse).toList();
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
}