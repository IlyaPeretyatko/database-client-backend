package ru.nsu.peretyatko.controller.equipments;

import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Equipment API")
@RestController
@RequestMapping("/equipments")
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

    @GetMapping("/by/type")
    public List<EquipmentResponse> getEquipmentsByType(@RequestParam String title) {
        return equipmentService.getEquipmentsByType(title);
    }

    @GetMapping("/by/category")
    public List<EquipmentResponse> getEquipmentsByCategory(@RequestParam String title) {
        return equipmentService.getEquipmentsByCategory(title);
    }

    @GetMapping("/unit/{id}/by/type")
    public List<EquipmentResponse> getEquipmentsByTypeUnit(@PathVariable int id,
                                                     @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeUnit(title,id);
    }

    @GetMapping("/unit/{id}/by/category")
    public List<EquipmentResponse> getEquipmentsByCategoryUnit(@PathVariable int id,
                                                         @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryUnit(title,id);
    }

    @GetMapping("/division/{id}/by/type")
    public List<EquipmentResponse> getEquipmentsByTypeDivision(@PathVariable int id,
                                                         @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeDivision(title,id);
    }

    @GetMapping("/division/{id}/by/category")
    public List<EquipmentResponse> getEquipmentsByCategoryDivision(@PathVariable int id,
                                                             @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryDivision(title,id);
    }

    @GetMapping("/brigade/{id}/by/type")
    public List<EquipmentResponse> getEquipmentsByTypeBrigade(@PathVariable int id,
                                                        @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeBrigade(title,id);
    }

    @GetMapping("/brigade/{id}/by/category")
    public List<EquipmentResponse> getEquipmentsByCategoryBrigade(@PathVariable int id,
                                                            @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryBrigade(title,id);
    }

    @GetMapping("/corps/{id}/by/type")
    public List<EquipmentResponse> getEquipmentsByTypeCorps(@PathVariable int id,
                                                      @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeCorps(title, id);
    }

    @GetMapping("/corps/{id}/by/category")
    public List<EquipmentResponse> getEquipmentsByCategoryCorps(@PathVariable int id,
                                                          @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryCorps(title, id);
    }

    @GetMapping("/army/{id}/by/type")
    public List<EquipmentResponse> getEquipmentsByTypeArmy(@PathVariable int id,
                                                     @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeArmy(title, id);
    }

    @GetMapping("/army/{id}/by/category")
    public List<EquipmentResponse> getEquipmentsByCategoryArmy(@PathVariable int id,
                                                         @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryArmy(title, id);
    }
}