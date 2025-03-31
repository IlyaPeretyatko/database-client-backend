package ru.nsu.peretyatko.controller.equipments;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.equipments.EquipmentPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentResponse;
import ru.nsu.peretyatko.service.equipments.EquipmentService;
import ru.nsu.peretyatko.validator.equipments.EquipmentValidator;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    private final EquipmentValidator equipmentValidator;

    @GetMapping
    public List<EquipmentResponse> getEquipments() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{id}")
    public EquipmentResponse getEquipmentById(@PathVariable int id) {
        return equipmentService.getEquipment(id);
    }

    @PostMapping
    public void createEquipment(@Valid @RequestBody EquipmentPostRequest equipmentPostRequest,
                               BindingResult bindingResult) {
        equipmentValidator.validate(equipmentPostRequest, bindingResult);
        equipmentService.createEquipment(equipmentPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateEquipment(@PathVariable int id,
                               @Valid @RequestBody EquipmentPatchRequest equipmentPatchRequest,
                               BindingResult bindingResult) {
        equipmentValidator.validate(equipmentPatchRequest, bindingResult);
        equipmentService.updateEquipment(id, equipmentPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable int id) {
        equipmentService.deleteEquipment(id);
    }
}