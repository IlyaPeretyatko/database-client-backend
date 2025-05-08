package ru.nsu.peretyatko.controller.equipments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyResponse;
import ru.nsu.peretyatko.service.equipments.EquipmentPropertyService;
import ru.nsu.peretyatko.validator.equipments.EquipmentPropertyValidator;

import java.util.List;

@Tag(name = "Equipment API")
@RestController
@RequestMapping("/equipments/properties")
@RequiredArgsConstructor
public class EquipmentPropertyController {

    private final EquipmentPropertyService equipmentPropertyService;

    private final EquipmentPropertyValidator equipmentPropertyValidator;

    @Operation(summary = "Получить перечень атрибутов военной техники")
    @GetMapping
    public List<EquipmentPropertyResponse> getEquipmentProperties() {
        return equipmentPropertyService.getEquipmentProperties();
    }

    @Operation(summary = "Получить атрибут военной техники по ID")
    @GetMapping("/{id}")
    public EquipmentPropertyResponse getEquipmentPropertyById(@PathVariable int id) {
        return equipmentPropertyService.getEquipmentProperty(id);
    }

    @Operation(summary = "Добавить атрибут военной техники")
    @PostMapping
    public void createEquipmentProperty(@Valid @RequestBody EquipmentPropertyPostRequest equipmentPropertyPostRequest,
                                BindingResult bindingResult) {
        equipmentPropertyValidator.validate(equipmentPropertyPostRequest, bindingResult);
        equipmentPropertyService.createEquipmentProperty(equipmentPropertyPostRequest);
    }

    @Operation(summary = "Изменить данные атрибута военной техники по ID")
    @PatchMapping("/{id}")
    public void updateEquipmentProperty(@PathVariable int id,
                                @Valid @RequestBody EquipmentPropertyPatchRequest equipmentPropertyPatchRequest,
                                BindingResult bindingResult) {
        equipmentPropertyValidator.validate(equipmentPropertyPatchRequest, bindingResult);
        equipmentPropertyService.updateEquipmentProperty(id, equipmentPropertyPatchRequest);
    }

    @Operation(summary = "Удалить атрибут военной техники по ID")
    @DeleteMapping("/{id}")
    public void deleteEquipmentProperty(@PathVariable int id) {
        equipmentPropertyService.deleteEquipmentProperty(id);
    }
}