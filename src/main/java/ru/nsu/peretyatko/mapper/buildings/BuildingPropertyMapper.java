package ru.nsu.peretyatko.mapper.buildings;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.model.buildings.BuildingProperty;
import ru.nsu.peretyatko.repository.buildings.BuildingRepository;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;

@Component
@RequiredArgsConstructor
public class BuildingPropertyMapper {

    private final UnitRepository unitRepository;

    private final BuildingRepository buildingRepository;

    public BuildingProperty toBuildingProperty(BuildingPropertyPostRequest buildingPropertyPostRequest) {
        BuildingProperty buildingProperty = new BuildingProperty();
        buildingProperty.setTitle(buildingPropertyPostRequest.getTitle());
        Building building = buildingRepository.findById(buildingPropertyPostRequest.getBuildingId()).orElseThrow(() -> new ServiceException(404, "Building was not found."));
        buildingProperty.setBuilding(building);
        buildingProperty.setValue(buildingPropertyPostRequest.getValue());
        return buildingProperty;
    }

    public BuildingPropertyResponse toBuildingPropertyResponse(BuildingProperty buildingProperty) {
        BuildingPropertyResponse buildingPropertyResponse = new BuildingPropertyResponse();
        buildingPropertyResponse.setId(buildingProperty.getId());
        buildingPropertyResponse.setTitle(buildingProperty.getTitle());
        buildingPropertyResponse.setValue(buildingProperty.getValue());
        buildingPropertyResponse.setBuildingId(buildingProperty.getBuilding().getId());
        return buildingPropertyResponse;
    }

    public void updateBuildingProperty(BuildingProperty buildingProperty, BuildingPropertyPatchRequest buildingPropertyPatchRequest) {
        if (buildingPropertyPatchRequest.getTitle() != null) {
            buildingProperty.setTitle(buildingPropertyPatchRequest.getTitle());
        }
        if (buildingPropertyPatchRequest.getValue() != null) {
            buildingProperty.setValue(buildingPropertyPatchRequest.getValue());
        }
        if (buildingPropertyPatchRequest.getBuildingId() != null) {
            Building building = buildingRepository.findById(buildingPropertyPatchRequest.getBuildingId()).orElseThrow(() -> new ServiceException(404, "Building was not found."));
            buildingProperty.setBuilding(building);
        }
    }
}
