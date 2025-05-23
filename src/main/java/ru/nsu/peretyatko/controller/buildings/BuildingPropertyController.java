package ru.nsu.peretyatko.controller.buildings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPostRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyResponse;
import ru.nsu.peretyatko.service.buildings.BuildingPropertyService;
import ru.nsu.peretyatko.validator.buildings.BuildingPropertyValidator;

import java.util.List;

@Tag(name = "Building API")
@RestController
@RequestMapping("/buildings/properties")
@RequiredArgsConstructor
public class BuildingPropertyController {

    private final BuildingPropertyService buildingPropertyService;

    private final BuildingPropertyValidator buildingPropertyValidator;

    @Operation(summary = "Получить перечень атрибутов сооружений")
    @GetMapping
    public Page<BuildingPropertyResponse> getBuildingProperties(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return buildingPropertyService.getBuildingProperties(page, size);
    }

    @Operation(summary = "Получить атрибут сооружения по ID")
    @GetMapping("/{id}")
    public BuildingPropertyResponse getBuildingPropertyById(@PathVariable int id) {
        return buildingPropertyService.getBuildingProperty(id);
    }

    @Operation(summary = "Создать атрибут сооружения")
    @PostMapping
    public void createBuildingProperty(@Valid @RequestBody BuildingPropertyPostRequest buildingPropertyPostRequest,
                               BindingResult bindingResult) {
        buildingPropertyValidator.validate(buildingPropertyPostRequest, bindingResult);
        buildingPropertyService.createBuildingProperty(buildingPropertyPostRequest);
    }

    @Operation(summary = "Изменить данные атрибута сооружения по ID")
    @PatchMapping("/{id}")
    public void updateBuildingProperty(@PathVariable int id,
                               @Valid @RequestBody BuildingPropertyPatchRequest buildingPropertyPatchRequest,
                               BindingResult bindingResult) {
        buildingPropertyValidator.validate(buildingPropertyPatchRequest, bindingResult);
        buildingPropertyService.updateBuildingProperty(id, buildingPropertyPatchRequest);
    }

    @Operation(summary = "Удалить атрибут сооружения по ID")
    @DeleteMapping("/{id}")
    public void deleteBuildingProperty(@PathVariable int id) {
        buildingPropertyService.deleteBuildingProperty(id);
    }
}