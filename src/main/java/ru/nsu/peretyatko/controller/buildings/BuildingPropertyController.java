package ru.nsu.peretyatko.controller.buildings;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyResponse;
import ru.nsu.peretyatko.service.buildings.BuildingPropertyService;
import ru.nsu.peretyatko.validator.buildings.BuildingPropertyValidator;

import java.util.List;

@RestController
@RequestMapping("/buildings/properties")
@RequiredArgsConstructor
public class BuildingPropertyController {

    private final BuildingPropertyService buildingPropertyService;

    private final BuildingPropertyValidator buildingPropertyValidator;

    @GetMapping
    public List<BuildingPropertyResponse> getBuildingProperties() {
        return buildingPropertyService.getBuildingProperties();
    }

    @GetMapping("/{id}")
    public BuildingPropertyResponse getBuildingPropertyById(@PathVariable int id) {
        return buildingPropertyService.getBuildingProperty(id);
    }

    @PostMapping
    public void createBuildingProperty(@Valid @RequestBody BuildingPropertyPostRequest buildingPropertyPostRequest,
                               BindingResult bindingResult) {
        buildingPropertyValidator.validate(buildingPropertyPostRequest, bindingResult);
        buildingPropertyService.createBuildingProperty(buildingPropertyPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateBuildingProperty(@PathVariable int id,
                               @Valid @RequestBody BuildingPropertyPatchRequest buildingPropertyPatchRequest,
                               BindingResult bindingResult) {
        buildingPropertyValidator.validate(buildingPropertyPatchRequest, bindingResult);
        buildingPropertyService.updateBuildingProperty(id, buildingPropertyPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBuildingProperty(@PathVariable int id) {
        buildingPropertyService.deleteBuildingProperty(id);
    }
}