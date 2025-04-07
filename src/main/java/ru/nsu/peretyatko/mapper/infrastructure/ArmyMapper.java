package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Army;
import ru.nsu.peretyatko.model.infrastructure.Brigade;
import ru.nsu.peretyatko.model.infrastructure.Corps;
import ru.nsu.peretyatko.model.infrastructure.Division;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.infrastructure.BrigadeRepository;
import ru.nsu.peretyatko.repository.infrastructure.CorpsRepository;
import ru.nsu.peretyatko.repository.infrastructure.DivisionRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArmyMapper {

    private final MilitaryRepository militaryRepository;

    private final BrigadeRepository brigadeRepository;

    private final DivisionRepository divisionRepository;

    private final CorpsRepository corpsRepository;

    private final BrigadeMapper brigadeMapper;

    private final CorpsMapper corpsMapper;

    private final DivisionMapper divisionMapper;

    public Army toArmy(ArmyPostRequest armyPostRequest) {
        Army army = new Army();
        army.setTitle(armyPostRequest.getTitle());
        Military military = militaryRepository.findById(armyPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        army.setCommander(military);
        if (armyPostRequest.getBrigadesId() != null) {
            Set<Brigade> brigades = armyPostRequest.getBrigadesId().stream()
                    .map(id -> brigadeRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Brigade with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            army.setBrigades(brigades);
        }
        if (armyPostRequest.getDivisionsId() != null) {
            Set<Division> divisions = armyPostRequest.getDivisionsId().stream()
                    .map(id -> divisionRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Division with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            army.setDivisions(divisions);
        }
        if (armyPostRequest.getCorpsId() != null) {
            Set<Corps> corps = armyPostRequest.getCorpsId().stream()
                    .map(id -> corpsRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Corps with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            army.setCorps(corps);
        }
        return army;
    }

    public ArmyResponse toArmyResponse(Army army) {
        ArmyResponse armyResponse = new ArmyResponse();
        armyResponse.setId(army.getId());
        armyResponse.setTitle(army.getTitle());
        armyResponse.setCommanderId(army.getCommander().getId());
        armyResponse.setBrigades(army.getBrigades().stream().map(brigadeMapper::toBrigadeResponse).collect(Collectors.toSet()));
        armyResponse.setCorps(army.getCorps().stream().map(corpsMapper::toCorpsResponse).collect(Collectors.toSet()));
        armyResponse.setDivisions(army.getDivisions().stream().map(divisionMapper::toDivisionResponse).collect(Collectors.toSet()));
        return armyResponse;
    }

    public void updateArmy(Army army, ArmyPatchRequest armyPatchRequest) {
        if (armyPatchRequest.getTitle() != null) {
            army.setTitle(armyPatchRequest.getTitle());
        }
        if (armyPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(armyPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            army.setCommander(military);
        }
        if (armyPatchRequest.getBrigadesId() != null) {
            Set<Brigade> brigades = armyPatchRequest.getBrigadesId().stream()
                    .map(id -> brigadeRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Brigade with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            army.setBrigades(brigades);
        }
        if (armyPatchRequest.getDivisionsId() != null) {
            Set<Division> divisions = armyPatchRequest.getDivisionsId().stream()
                    .map(id -> divisionRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Division with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            army.setDivisions(divisions);
        }
        if (armyPatchRequest.getCorpsId() != null) {
            Set<Corps> corps = armyPatchRequest.getCorpsId().stream()
                    .map(id -> corpsRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Corps with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            army.setCorps(corps);
        }
    }
}
