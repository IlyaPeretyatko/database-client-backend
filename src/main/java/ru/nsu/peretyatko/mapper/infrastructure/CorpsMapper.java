package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Corps;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CorpsMapper {

    private final MilitaryRepository militaryRepository;

    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    public Corps toCorps(CorpsPostRequest corpsPostRequest) {
        Corps corps = new Corps();
        corps.setTitle(corpsPostRequest.getTitle());
        Military military = militaryRepository.findById(corpsPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        corps.setCommander(military);
        if (corpsPostRequest.getUnitsId() != null) {
            Set<Unit> units = corpsPostRequest.getUnitsId().stream()
                    .map(id -> unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            corps.setUnits(units);
        }
        return corps;
    }

    public CorpsResponse toCorpsResponse(Corps corps) {
        CorpsResponse corpsResponse = new CorpsResponse();
        corpsResponse.setId(corps.getId());
        corpsResponse.setTitle(corps.getTitle());
        corpsResponse.setCommanderId(corps.getCommander().getId());
        corpsResponse.setUnits(corps.getUnits().stream().map(unitMapper::toUnitResponse).collect(Collectors.toSet()));
        return corpsResponse;
    }

    public void updateCorps(Corps corps, CorpsPatchRequest corpsPatchRequest) {
        if (corpsPatchRequest.getTitle() != null) {
            corps.setTitle(corpsPatchRequest.getTitle());
        }
        if (corpsPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(corpsPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            corps.setCommander(military);
        }
        if (corpsPatchRequest.getUnitsId() != null) {
            Set<Unit> units = corpsPatchRequest.getUnitsId().stream()
                    .map(id -> unitRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Unit with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            corps.setUnits(units);
        }
    }
}
