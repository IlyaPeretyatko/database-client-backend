package ru.nsu.peretyatko.controller.buildings;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.buildings.BuildingPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingResponse;
import ru.nsu.peretyatko.service.buildings.BuildingService;
import ru.nsu.peretyatko.validator.buildings.BuildingValidator;

import java.util.List;

@Tag(name = "Building API")
@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    private final BuildingValidator buildingValidator;

    @GetMapping
    public List<BuildingResponse> getBuildings() {
        return buildingService.getBuildings();
    }

    @GetMapping("/{id}")
    public BuildingResponse getBuildingById(@PathVariable int id) {
        return buildingService.getBuilding(id);
    }

    @PostMapping
    public void createBuilding(@Valid @RequestBody BuildingPostRequest buildingPostRequest,
                           BindingResult bindingResult) {
        buildingValidator.validate(buildingPostRequest, bindingResult);
        buildingService.createBuilding(buildingPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateBuilding(@PathVariable int id,
                           @Valid @RequestBody BuildingPatchRequest buildingPatchRequest,
                           BindingResult bindingResult) {
        buildingValidator.validate(buildingPatchRequest, bindingResult);
        buildingService.updateBuilding(id, buildingPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable int id) {
        buildingService.deleteBuilding(id);
    }

    @GetMapping("/by/unit/{id}")
    public List<BuildingResponse> getBuildingsUnit(@PathVariable int id) {
        return buildingService.getBuildingsUnit(id);
    }

    @GetMapping("/of/separations")
    public List<BuildingResponse> getBuildingsOfSeparation() {
        return buildingService.getBuildingsOfSeparation();
    }

    @GetMapping("/of/no-separations")
    public List<BuildingResponse> getBuildingsOfNoSeparation() {
        return buildingService.getBuildingsOfNoSeparation();
    }
}