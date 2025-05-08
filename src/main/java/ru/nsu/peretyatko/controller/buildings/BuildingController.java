package ru.nsu.peretyatko.controller.buildings;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Получить перечень сооружений")
    @GetMapping
    public List<BuildingResponse> getBuildings() {
        return buildingService.getBuildings();
    }

    @Operation(summary = "Получить сооружение по ID")
    @GetMapping("/{id}")
    public BuildingResponse getBuildingById(@PathVariable int id) {
        return buildingService.getBuilding(id);
    }

    @Operation(summary = "Добавить сооружение")
    @PostMapping
    public void createBuilding(@Valid @RequestBody BuildingPostRequest buildingPostRequest,
                           BindingResult bindingResult) {
        buildingValidator.validate(buildingPostRequest, bindingResult);
        buildingService.createBuilding(buildingPostRequest);
    }

    @Operation(summary = "Изменить данные сооружения по ID")
    @PatchMapping("/{id}")
    public void updateBuilding(@PathVariable int id,
                           @Valid @RequestBody BuildingPatchRequest buildingPatchRequest,
                           BindingResult bindingResult) {
        buildingValidator.validate(buildingPatchRequest, bindingResult);
        buildingService.updateBuilding(id, buildingPatchRequest);
    }

    @Operation(summary = "Удалить сооружение по ID")
    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable int id) {
        buildingService.deleteBuilding(id);
    }

    @Operation(summary = "Получить все сооружения военной части")
    @GetMapping("/by/unit/{id}")
    public List<BuildingResponse> getBuildingsUnit(@PathVariable int id) {
        return buildingService.getBuildingsUnit(id);
    }

    @Operation(summary = "Получить все сооружения военных частей у который есть подразделения")
    @GetMapping("/of/separations")
    public List<BuildingResponse> getBuildingsOfSeparation() {
        return buildingService.getBuildingsOfSeparation();
    }

    @Operation(summary = "Получить все сооружения военных частей у который нету подразделений")
    @GetMapping("/of/no-separations")
    public List<BuildingResponse> getBuildingsOfNoSeparation() {
        return buildingService.getBuildingsOfNoSeparation();
    }
}