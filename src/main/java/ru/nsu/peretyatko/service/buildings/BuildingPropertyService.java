package ru.nsu.peretyatko.service.buildings;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.buildings.BuildingPropertyMapper;
import ru.nsu.peretyatko.model.buildings.BuildingProperty;
import ru.nsu.peretyatko.repository.buildings.BuildingPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingPropertyService {
    private final BuildingPropertyRepository buildingPropertyRepository;

    private final BuildingPropertyMapper buildingPropertyMapper;

    @Transactional(readOnly = true)
    public Page<BuildingPropertyResponse> getBuildingProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return buildingPropertyRepository.findAll(pageable).map(buildingPropertyMapper::toBuildingPropertyResponse);
    }

    @Transactional(readOnly = true)
    public BuildingPropertyResponse getBuildingProperty(int id) {
        BuildingProperty buildingProperty = buildingPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "BuildingProperty was not found."));
        return buildingPropertyMapper.toBuildingPropertyResponse(buildingProperty);
    }

    @Transactional
    public void createBuildingProperty(BuildingPropertyPostRequest buildingPropertyPostRequest) {
        buildingPropertyRepository.save(buildingPropertyMapper.toBuildingProperty(buildingPropertyPostRequest));
    }

    @Transactional
    public void updateBuildingProperty(int id, BuildingPropertyPatchRequest buildingPropertyPatchRequest) {
        BuildingProperty buildingProperty = buildingPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "BuildingProperty was not found."));
        buildingPropertyMapper.updateBuildingProperty(buildingProperty, buildingPropertyPatchRequest);
        buildingPropertyRepository.save(buildingProperty);
    }

    @Transactional
    public void deleteBuildingProperty(int id) {
        if (!buildingPropertyRepository.existsById(id)) {
            throw new ServiceException(404, "BuildingProperty was not found.");
        }
        buildingPropertyRepository.deleteById(id);
    }
}
