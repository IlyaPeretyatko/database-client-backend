package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Division;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DivisionMapper {

    private final MilitaryRepository militaryRepository;

    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    public Division toDivision(DivisionPostRequest divisionPostRequest) {
        Division division = new Division();
        division.setTitle(divisionPostRequest.getTitle());
        Military military = militaryRepository.findById(divisionPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        division.setCommander(military);
        if (divisionPostRequest.getUnitsId() != null) {
            Set<Unit> units = divisionPostRequest.getUnitsId().stream()
                    .map(id -> unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            division.setUnits(units);
        }
        return division;
    }

    public DivisionResponse toDivisionResponse(Division division) {
        DivisionResponse divisionResponse = new DivisionResponse();
        divisionResponse.setId(division.getId());
        divisionResponse.setTitle(division.getTitle());
        divisionResponse.setCommanderId(division.getCommander().getId());
        divisionResponse.setUnits(division.getUnits().stream().map(unitMapper::toUnitResponse).collect(Collectors.toSet()));
        return divisionResponse;
    }

    public void updateDivision(Division division, DivisionPatchRequest divisionPatchRequest) {
        if (divisionPatchRequest.getTitle() != null) {
            division.setTitle(divisionPatchRequest.getTitle());
        }
        if (divisionPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(divisionPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            division.setCommander(military);
        }
        if (divisionPatchRequest.getUnitsId() != null) {
            Set<Unit> units = divisionPatchRequest.getUnitsId().stream()
                    .map(id -> unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            division.setUnits(units);
        }
    }
}
