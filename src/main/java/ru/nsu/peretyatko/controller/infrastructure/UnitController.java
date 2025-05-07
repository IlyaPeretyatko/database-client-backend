package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitResponse;
import ru.nsu.peretyatko.service.infrastructure.UnitService;
import ru.nsu.peretyatko.validator.infrastructure.UnitValidator;

import java.util.List;

@Tag(name = "Unit API")
@RestController
@RequestMapping("/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    private final UnitValidator unitValidator;

    @GetMapping
    public List<UnitResponse> getUnits() {
        return unitService.getUnits();
    }

    @GetMapping("/{id}")
    public UnitResponse getUnitById(@PathVariable int id) {
        return unitService.getUnit(id);
    }

    @PostMapping
    public void createUnit(@Valid @RequestBody UnitPostRequest unitPostRequest,
                           BindingResult bindingResult) {
        unitValidator.validate(unitPostRequest, bindingResult);
        unitService.createUnit(unitPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateUnit(@PathVariable int id,
                           @Valid @RequestBody UnitPatchRequest unitPatchRequest,
                           BindingResult bindingResult) {
        unitValidator.validate(unitPatchRequest, bindingResult);
        unitService.updateUnit(id, unitPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable int id) {
        unitService.deleteUnit(id);
    }

    @GetMapping("/by/division/{id}")
    public List<UnitResponse> getUnitsByDivision(@PathVariable int id) {
        return unitService.getUnitsByDivision(id);
    }

    @GetMapping("/by/brigade/{id}")
    public List<UnitResponse> getUnitsByBrigade(@PathVariable int id) {
        return unitService.getUnitsByBrigade(id);
    }

    @GetMapping("/by/corps/{id}")
    public List<UnitResponse> getUnitsByCorps(@PathVariable int id) {
        return unitService.getUnitsByCorps(id);
    }

    @GetMapping("/by/army/{id}")
    public List<UnitResponse> getUnitsByArmy(@PathVariable int id) {
        return unitService.getUnitsByArmy(id);
    }

    @GetMapping("/with/equipment")
    public List<UnitResponse> getUnitsWithEquipmentTypeCount(@RequestParam String type,
                                                             @RequestParam(required = false, defaultValue = "0") int minCount) {
        return unitService.getUnitsWithEquipmentTypeCount(type, minCount);
    }

    @GetMapping("/without/equipment")
    public List<UnitResponse> getUnitsWithoutEquipmentType(@RequestParam String type)  {
        return unitService.getUnitsWithoutEquipmentType(type);
    }

    @GetMapping("/with/weapon")
    public List<UnitResponse> getUnitsWithWeaponTypeCount(@RequestParam String type,
                                                          @RequestParam(required = false, defaultValue = "0") int minCount) {
        return unitService.getUnitsWithWeaponTypeCount(type, minCount);
    }

    @GetMapping("/without/weapon")
    public List<UnitResponse> getUnitsWithoutWeaponType(@RequestParam String type) {
        return unitService.getUnitsWithoutWeaponType(type);
    }
}