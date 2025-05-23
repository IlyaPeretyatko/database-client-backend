package ru.nsu.peretyatko.controller.equipments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeResponse;
import ru.nsu.peretyatko.service.equipments.EquipmentTypeService;
import ru.nsu.peretyatko.validator.equipments.EquipmentTypeValidator;

import java.util.List;

@Tag(name = "Equipment API")
@RestController
@RequestMapping("/equipments/types")
@RequiredArgsConstructor
public class EquipmentTypeController {

    private final EquipmentTypeService equipmentTypeService;

    private final EquipmentTypeValidator equipmentTypeValidator;

    @Operation(summary = "Получить перечень видов военной техники")
    @GetMapping
    public Page<EquipmentTypeResponse> getEquipmentTypes(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return equipmentTypeService.getEquipmentTypes(page, size);
    }

    @Operation(summary = "Получить вид военной техники по ID")
    @GetMapping("/{id}")
    public EquipmentTypeResponse getEquipmentTypeById(@PathVariable int id) {
        return equipmentTypeService.getEquipmentType(id);
    }

    @Operation(summary = "Добавить вид военной техники")
    @PostMapping
    public void createEquipmentType(@Valid @RequestBody EquipmentTypeRequest equipmentTypeRequest,
                                        BindingResult bindingResult) {
        equipmentTypeValidator.validate(equipmentTypeRequest, bindingResult);
        equipmentTypeService.createEquipmentType(equipmentTypeRequest);
    }

    @Operation(summary = "Удалить вид военной техники по ID")
    @DeleteMapping("/{id}")
    public void deleteEquipmentType(@PathVariable int id) {
        equipmentTypeService.deleteEquipmentType(id);
    }
}
