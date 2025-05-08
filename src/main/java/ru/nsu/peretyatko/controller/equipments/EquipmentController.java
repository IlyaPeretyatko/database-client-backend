package ru.nsu.peretyatko.controller.equipments;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Получить перечень военной техники")
    @GetMapping
    public List<EquipmentResponse> getEquipments() {
        return equipmentService.getEquipments();
    }

    @Operation(summary = "Получить военную техника по ID")
    @GetMapping("/{id}")
    public EquipmentResponse getEquipmentById(@PathVariable int id) {
        return equipmentService.getEquipment(id);
    }

    @Operation(summary = "Добавить военную технику")
    @PostMapping
    public void createEquipment(@Valid @RequestBody EquipmentPostRequest equipmentPostRequest,
                               BindingResult bindingResult) {
        equipmentValidator.validate(equipmentPostRequest, bindingResult);
        equipmentService.createEquipment(equipmentPostRequest);
    }

    @Operation(summary = "Изменить данные военной техники по ID")
    @PatchMapping("/{id}")
    public void updateEquipment(@PathVariable int id,
                               @Valid @RequestBody EquipmentPatchRequest equipmentPatchRequest,
                               BindingResult bindingResult) {
        equipmentValidator.validate(equipmentPatchRequest, bindingResult);
        equipmentService.updateEquipment(id, equipmentPatchRequest);
    }

    @Operation(summary = "Удалить военную технику по ID")
    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable int id) {
        equipmentService.deleteEquipment(id);
    }

    @Operation(summary = "Получить перечень военной техники по виду")
    @GetMapping("/by/type")
    public List<EquipmentResponse> getEquipmentsByType(@RequestParam String title) {
        return equipmentService.getEquipmentsByType(title);
    }

    @Operation(summary = "Получить перечень военной техники по категории")
    @GetMapping("/by/category")
    public List<EquipmentResponse> getEquipmentsByCategory(@RequestParam String title) {
        return equipmentService.getEquipmentsByCategory(title);
    }

    @Operation(summary = "Получить перечень военной техники военной части по виду")
    @GetMapping("/by/type/unit/{id}")
    public List<EquipmentResponse> getEquipmentsByTypeUnit(@PathVariable int id,
                                                     @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeUnit(title,id);
    }

    @Operation(summary = "Получить перечень военной техники военной части по категории")
    @GetMapping("/by/category/unit/{id}")
    public List<EquipmentResponse> getEquipmentsByCategoryUnit(@PathVariable int id,
                                                         @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryUnit(title,id);
    }

    @Operation(summary = "Получить перечень военной техники дивизии по виду")
    @GetMapping("/by/type/division/{id}")
    public List<EquipmentResponse> getEquipmentsByTypeDivision(@PathVariable int id,
                                                         @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeDivision(title,id);
    }

    @Operation(summary = "Получить перечень военной техники дивизии по категории")
    @GetMapping("/by/category/division/{id}")
    public List<EquipmentResponse> getEquipmentsByCategoryDivision(@PathVariable int id,
                                                             @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryDivision(title,id);
    }

    @Operation(summary = "Получить перечень военной техники бригады по виду")
    @GetMapping("/by/type/brigade/{id}")
    public List<EquipmentResponse> getEquipmentsByTypeBrigade(@PathVariable int id,
                                                        @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeBrigade(title,id);
    }

    @Operation(summary = "Получить перечень военной техники бригады по категории")
    @GetMapping("/by/category/brigade/{id}")
    public List<EquipmentResponse> getEquipmentsByCategoryBrigade(@PathVariable int id,
                                                            @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryBrigade(title,id);
    }

    @Operation(summary = "Получить перечень военной техники корпуса по виду")
    @GetMapping("/by/type/corps/{id}")
    public List<EquipmentResponse> getEquipmentsByTypeCorps(@PathVariable int id,
                                                      @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeCorps(title, id);
    }

    @Operation(summary = "Получить перечень военной техники корпуса по категории")
    @GetMapping("/by/category/corps/{id}")
    public List<EquipmentResponse> getEquipmentsByCategoryCorps(@PathVariable int id,
                                                          @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryCorps(title, id);
    }

    @Operation(summary = "Получить перечень военной техники армии по виду")
    @GetMapping("/by/type/army/{id}")
    public List<EquipmentResponse> getEquipmentsByTypeArmy(@PathVariable int id,
                                                     @RequestParam String title) {
        return equipmentService.getEquipmentsByTypeArmy(title, id);
    }

    @Operation(summary = "Получить перечень военной техники по категории")
    @GetMapping("/by/category/army/{id}")
    public List<EquipmentResponse> getEquipmentsByCategoryArmy(@PathVariable int id,
                                                         @RequestParam String title) {
        return equipmentService.getEquipmentsByCategoryArmy(title, id);
    }
}