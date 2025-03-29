package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

@Component
@RequiredArgsConstructor
public class UnitMapper {

    private final MilitaryRepository militaryRepository;

    public Unit toUnit(UnitPostRequest unitPostRequest) {
        Unit unit = new Unit();
        unit.setTitle(unitPostRequest.getTitle());
        unit.setLatitude(unitPostRequest.getLatitude());
        unit.setLongitude(unitPostRequest.getLongitude());
        Military military = militaryRepository.findById(unitPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        unit.setCommander(military);
        return unit;
    }

    public UnitResponse toUnitResponse(Unit unit) {
        UnitResponse unitResponse = new UnitResponse();
        unitResponse.setId(unit.getId());
        unitResponse.setTitle(unit.getTitle());
        unitResponse.setLatitude(unit.getLatitude());
        unitResponse.setLongitude(unit.getLongitude());
        unitResponse.setCommanderId(unit.getCommander().getId());
        return unitResponse;
    }

    public void updateUnit(Unit unit, UnitPatchRequest unitPatchRequest) {
        if (unitPatchRequest.getTitle() != null) {
            unit.setTitle(unitPatchRequest.getTitle());
        }
        if (unitPatchRequest.getLatitude() != null) {
            unit.setLatitude(unitPatchRequest.getLatitude());
        }
        if (unitPatchRequest.getLongitude() != null) {
            unit.setLongitude(unitPatchRequest.getLongitude());
        }
        if (unitPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(unitPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander id was not found."));
            unit.setCommander(military);
        }
    }
}
