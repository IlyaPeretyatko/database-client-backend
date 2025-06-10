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
    public Page<UnitResponse> getUnitsByDivision(@PathVariable int id,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsByDivision(id, page, size);
    }

    @Operation(summary = "Получить перечень военных частей бригады")
    @GetMapping("/by/brigade/{id}")
    public Page<UnitResponse> getUnitsByBrigade(@PathVariable int id,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsByBrigade(id, page, size);
    }

    @Operation(summary = "Получить перечень военных частей корпусов")
    @GetMapping("/by/corps/{id}")
    public Page<UnitResponse> getUnitsByCorps(@PathVariable int id,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsByCorps(id, page, size);
    }

    @Operation(summary = "Получить перечень военных частей армии")
    @GetMapping("/by/army/{id}")
    public Page<UnitResponse> getUnitsByArmy(@PathVariable int id,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsByArmy(id, page, size);
    }

    @Operation(summary = "Получить перечень военных частей c наличием конкретного вида военной техники")
    @GetMapping("/with/equipment")
    public Page<UnitResponse> getUnitsWithEquipmentTypeCount(@RequestParam String type,
                                                             @RequestParam(required = false, defaultValue = "0") int minCount,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsWithEquipmentTypeCount(type, minCount, page, size);
    }

    @Operation(summary = "Получить перечень военных частей у которых нету в наличии конкретного вида военной техники")
    @GetMapping("/without/equipment")
    public Page<UnitResponse> getUnitsWithoutEquipmentType(@RequestParam String type,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size)  {
        return unitService.getUnitsWithoutEquipmentType(type, page, size);
    }

    @Operation(summary = "Получить перечень военных частей c наличием конкретного вида оружия")
    @GetMapping("/with/weapon")
    public Page<UnitResponse> getUnitsWithWeaponTypeCount(@RequestParam String type,
                                                          @RequestParam(required = false, defaultValue = "0") int minCount,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsWithWeaponTypeCount(type, minCount, page, size);
    }

    @Operation(summary = "Получить перечень военных частей у которых нету в наличии конкретного вида оружия")
    @GetMapping("/without/weapon")
    public Page<UnitResponse> getUnitsWithoutWeaponType(@RequestParam String type,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return unitService.getUnitsWithoutWeaponType(type, page, size);
    }
}