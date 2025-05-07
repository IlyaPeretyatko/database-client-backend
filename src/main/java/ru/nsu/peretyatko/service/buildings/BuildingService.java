package ru.nsu.peretyatko.service.buildings;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.buildings.BuildingPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.buildings.BuildingMapper;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.repository.buildings.BuildingCustomRepository;
import ru.nsu.peretyatko.repository.buildings.BuildingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingCustomRepository buildingCustomRepository;

    private final BuildingRepository buildingRepository;

    private final BuildingMapper buildingMapper;

    @Transactional(readOnly = true)
    public List<BuildingResponse> getBuildings() {
        return buildingRepository.findAll().stream().map(buildingMapper::toBuildingResponse).toList();
    }

    @Transactional(readOnly = true)
    public BuildingResponse getBuilding(int id) {
        Building building = buildingRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Building was not found."));
        return buildingMapper.toBuildingResponse(building);
    }

    @Transactional
    public void createBuilding(BuildingPostRequest buildingPostRequest) {
        buildingRepository.save(buildingMapper.toBuilding(buildingPostRequest));
    }

    @Transactional
    public void updateBuilding(int id, BuildingPatchRequest buildingPatchRequest) {
        Building building = buildingRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Building was not found."));
        buildingMapper.updateBuilding(building, buildingPatchRequest);
        buildingRepository.save(building);
    }

    @Transactional
    public void deleteBuilding(int id) {
        if (!buildingRepository.existsById(id)) {
            throw new ServiceException(404, "Building was not found.");
        }
        buildingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BuildingResponse> getBuildingsUnit(int unitId) {
        return buildingCustomRepository.findBuildingsUnit(unitId).stream().map(buildingMapper::toBuildingResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<BuildingResponse> getBuildingsOfSeparation() {
        return buildingCustomRepository.findBuildingsOfSeparation().stream().map(buildingMapper::toBuildingResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<BuildingResponse> getBuildingsOfNoSeparation() {
        return buildingCustomRepository.findBuildingsOfNoSeparation().stream().map(buildingMapper::toBuildingResponse).toList();
    }

}