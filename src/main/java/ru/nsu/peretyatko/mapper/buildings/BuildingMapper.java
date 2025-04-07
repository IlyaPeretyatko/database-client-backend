package ru.nsu.peretyatko.mapper.buildings;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.buildings.BuildingPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;

@Component
@RequiredArgsConstructor
public class BuildingMapper {

    private final UnitRepository unitRepository;

    public Building toBuilding(BuildingPostRequest buildingPostRequest) {
        Building building = new Building();
        building.setTitle(buildingPostRequest.getTitle());
        building.setAddress(buildingPostRequest.getAddress());
        Unit unit = unitRepository.findById(buildingPostRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
        building.setUnit(unit);
        return building;
    }

    public BuildingResponse toBuildingResponse(Building building) {
        BuildingResponse buildingResponse = new BuildingResponse();
        buildingResponse.setId(building.getId());
        buildingResponse.setTitle(building.getTitle());
        buildingResponse.setAddress(building.getAddress());
        buildingResponse.setUnitId(building.getUnit().getId());
        return buildingResponse;
    }

    public void updateBuilding(Building building, BuildingPatchRequest buildingPatchRequest) {
        if (buildingPatchRequest.getTitle() != null) {
            building.setTitle(buildingPatchRequest.getTitle());
        }
        if (buildingPatchRequest.getAddress() != null) {
            building.setAddress(buildingPatchRequest.getAddress());
        }
        if (buildingPatchRequest.getUnitId() != null) {
            Unit unit = unitRepository.findById(buildingPatchRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
            building.setUnit(unit);
        }
    }
}
