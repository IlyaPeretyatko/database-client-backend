package ru.nsu.peretyatko.controller.equipments;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeResponse;
import ru.nsu.peretyatko.service.equipments.EquipmentTypeService;
import ru.nsu.peretyatko.validator.equipments.EquipmentTypeValidator;

import java.util.List;

@RestController
@RequestMapping("/api/equipment/type")
@RequiredArgsConstructor
public class EquipmentTypeController {

    private final EquipmentTypeService equipmentTypeService;

    private final EquipmentTypeValidator equipmentTypeValidator;

    @GetMapping
    public List<EquipmentTypeResponse> getEquipmentTypes() {
        return equipmentTypeService.getEquipmentTypes();
    }

    @GetMapping("/{id}")
    public EquipmentTypeResponse getEquipmentTypeById(@PathVariable int id) {
        return equipmentTypeService.getEquipmentType(id);
    }

    @PostMapping
    public void createEquipmentType(@Valid @RequestBody EquipmentTypeRequest equipmentTypeRequest,
                                        BindingResult bindingResult) {
        equipmentTypeValidator.validate(equipmentTypeRequest, bindingResult);
        equipmentTypeService.createEquipmentType(equipmentTypeRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentType(@PathVariable int id) {
        equipmentTypeService.deleteEquipmentType(id);
    }
}
