package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Operation(summary = "Получить перечень военных частей")
    @GetMapping
    public Page<UnitResponse> getUnits(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnits(page, size);
    }

    @Operation(summary = "Получить военную часть по ID")
    @GetMapping("/{id}")
    public UnitResponse getUnitById(@PathVariable int id) {
        return unitService.getUnit(id);
    }

    @Operation(summary = "Добавить военную часть")
    @PostMapping
    public void createUnit(@Valid @RequestBody UnitPostRequest unitPostRequest,
                           BindingResult bindingResult) {
        unitValidator.validate(unitPostRequest, bindingResult);
        unitService.createUnit(unitPostRequest);
    }

    @Operation(summary = "Изменить данные военной части по ID")
    @PatchMapping("/{id}")
    public void updateUnit(@PathVariable int id,
                           @Valid @RequestBody UnitPatchRequest unitPatchRequest,
                           BindingResult bindingResult) {
        unitValidator.validate(unitPatchRequest, bindingResult);
        unitService.updateUnit(id, unitPatchRequest);
    }

    @Operation(summary = "Удалить военную часть по ID")
    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable int id) {
        unitService.deleteUnit(id);
    }

    @Operation(summary = "Получить перечень военных частей дивизии")
    @GetMapping("/by/division/{id}")
    public List<UnitResponse> getUnitsByDivision(@PathVariable int id) {
        return unitService.getUnitsByDivision(id);
    }

    @Operation(summary = "Получить перечень военных частей бригады")
    @GetMapping("/by/brigade/{id}")
    public List<UnitResponse> getUnitsByBrigade(@PathVariable int id) {
        return unitService.getUnitsByBrigade(id);
    }

    @Operation(summary = "Получить перечень военных частей корпусов")
    @GetMapping("/by/corps/{id}")
    public List<UnitResponse> getUnitsByCorps(@PathVariable int id) {
        return unitService.getUnitsByCorps(id);
    }

    @Operation(summary = "Получить перечень военных частей армии")
    @GetMapping("/by/army/{id}")
    public List<UnitResponse> getUnitsByArmy(@PathVariable int id) {
        return unitService.getUnitsByArmy(id);
    }

    @Operation(summary = "Получить перечень военных частей c наличием конкретного вида военной техники")
    @GetMapping("/with/equipment")
    public List<UnitResponse> getUnitsWithEquipmentTypeCount(@RequestParam String type,
                                                             @RequestParam(required = false, defaultValue = "0") int minCount) {
        return unitService.getUnitsWithEquipmentTypeCount(type, minCount);
    }

    @Operation(summary = "Получить перечень военных частей у которых нету в наличии конкретного вида военной техники")
    @GetMapping("/without/equipment")
    public List<UnitResponse> getUnitsWithoutEquipmentType(@RequestParam String type)  {
        return unitService.getUnitsWithoutEquipmentType(type);
    }

    @Operation(summary = "Получить перечень военных частей c наличием конкретного вида оружия")
    @GetMapping("/with/weapon")
    public List<UnitResponse> getUnitsWithWeaponTypeCount(@RequestParam String type,
                                                          @RequestParam(required = false, defaultValue = "0") int minCount) {
        return unitService.getUnitsWithWeaponTypeCount(type, minCount);
    }

    @Operation(summary = "Получить перечень военных частей у которых нету в наличии конкретного вида оружия")
    @GetMapping("/without/weapon")
    public List<UnitResponse> getUnitsWithoutWeaponType(@RequestParam String type) {
        return unitService.getUnitsWithoutWeaponType(type);
    }
}