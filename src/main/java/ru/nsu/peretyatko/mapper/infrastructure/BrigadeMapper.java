package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePostRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadeResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Brigade;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrigadeMapper {

    private final MilitaryRepository militaryRepository;

    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    public Brigade toBrigade(BrigadePostRequest brigadePostRequest) {
        Brigade brigade = new Brigade();
        brigade.setTitle(brigadePostRequest.getTitle());
        Military military = militaryRepository.findById(brigadePostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        brigade.setCommander(military);
        Set<Unit> units = brigadePostRequest.getUnitsId().stream()
                .map(id -> unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit with id:" + id + " was not found.")))
                .collect(Collectors.toSet());
        brigade.setUnits(units);
        return brigade;
    }

    public BrigadeResponse toBrigadeResponse(Brigade brigade) {
        BrigadeResponse brigadeResponse = new BrigadeResponse();
        brigadeResponse.setId(brigade.getId());
        brigadeResponse.setTitle(brigade.getTitle());
        brigadeResponse.setCommanderId(brigade.getCommander().getId());
        brigadeResponse.setUnits(brigade.getUnits().stream().map(unitMapper::toUnitResponse).collect(Collectors.toSet()));
        return brigadeResponse;
    }

    public void updateBrigade(Brigade brigade, BrigadePatchRequest brigadePatchRequest) {
        if (brigadePatchRequest.getTitle() != null) {
            brigade.setTitle(brigadePatchRequest.getTitle());
        }
        if (brigadePatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(brigadePatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            brigade.setCommander(military);
        }
        if (brigadePatchRequest.getUnitsId() != null) {
            Set<Unit> units = brigadePatchRequest.getUnitsId().stream()
                    .map(id -> unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            brigade.setUnits(units);
        }
    }
}
