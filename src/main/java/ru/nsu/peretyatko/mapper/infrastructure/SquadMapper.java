package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.SquadPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.buildings.BuildingMapper;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.model.infrastructure.Platoon;
import ru.nsu.peretyatko.model.infrastructure.Squad;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.buildings.BuildingRepository;
import ru.nsu.peretyatko.repository.infrastructure.PlatoonRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SquadMapper {
    private final MilitaryRepository militaryRepository;

    private final PlatoonRepository platoonRepository;

    private final BuildingRepository buildingRepository;

    private final BuildingMapper buildingMapper;

    public Squad toSquad(SquadPostRequest squadPostRequest) {
        Squad squad = new Squad();
        squad.setTitle(squadPostRequest.getTitle());
        Military military = militaryRepository.findById(squadPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        squad.setCommander(military);
        Platoon platoon = platoonRepository.findById(squadPostRequest.getPlatoonId()).orElseThrow(() -> new ServiceException(404, "Platoon eas not found."));
        squad.setPlatoon(platoon);
        Set<Building> buildings = squadPostRequest.getBuildingsId().stream()
                .map(id -> buildingRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Building with id:" + id + " was not found.")))
                .collect(Collectors.toSet());
        squad.setBuildings(buildings);
        return squad;
    }

    public SquadResponse toSquadResponse(Squad squad) {
        SquadResponse squadResponse = new SquadResponse();
        squadResponse.setId(squad.getId());
        squadResponse.setTitle(squad.getTitle());
        squadResponse.setCommanderId(squad.getCommander().getId());
        squadResponse.setPlatoonId(squad.getPlatoon().getId());
        squadResponse.setBuildings(squad.getBuildings().stream().map(buildingMapper::toBuildingResponse).collect(Collectors.toSet()));
        return squadResponse;
    }

    public void updateSquad(Squad squad, SquadPatchRequest squadPatchRequest) {
        if (squadPatchRequest.getTitle() != null) {
            squad.setTitle(squadPatchRequest.getTitle());
        }
        if (squadPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(squadPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            squad.setCommander(military);
        }
        if (squadPatchRequest.getPlatoonId() != null) {
            Platoon platoon = platoonRepository.findById(squadPatchRequest.getPlatoonId()).orElseThrow(() -> new ServiceException(404, "Platoon eas not found."));
            squad.setPlatoon(platoon);
        }
        if (squadPatchRequest.getBuildingsId() != null) {
            Set<Building> buildings = squadPatchRequest.getBuildingsId().stream()
                    .map(id -> buildingRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Building with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            squad.setBuildings(buildings);
        }
    }
}
