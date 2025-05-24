package ru.nsu.peretyatko.controller.equipments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.equipments.EquipmentPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentResponse;
import ru.nsu.peretyatko.service.equipments.EquipmentService;
import ru.nsu.peretyatko.validator.equipments.EquipmentValidator;


@Tag(name = "Equipment API")
@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    private final EquipmentValidator equipmentValidator;

    @Operation(summary = "Получить перечень военной техники")
    @GetMapping
    public Page<EquipmentResponse> getEquipments(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipments(page, size);
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
    public Page<EquipmentResponse> getEquipmentsByType(@RequestParam String title,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByType(title, page, size);
    }

    @Operation(summary = "Получить перечень военной техники по категории")
    @GetMapping("/by/category")
    public Page<EquipmentResponse> getEquipmentsByCategory(@RequestParam String title,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByCategory(title, page, size);
    }

    @Operation(summary = "Получить перечень военной техники военной части по виду")
    @GetMapping("/by/type/unit/{id}")
    public Page<EquipmentResponse> getEquipmentsByTypeUnit(@PathVariable int id,
                                                           @RequestParam String title,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByTypeUnit(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники военной части по категории")
    @GetMapping("/by/category/unit/{id}")
    public Page<EquipmentResponse> getEquipmentsByCategoryUnit(@PathVariable int id,
                                                               @RequestParam String title,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByCategoryUnit(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники дивизии по виду")
    @GetMapping("/by/type/division/{id}")
    public Page<EquipmentResponse> getEquipmentsByTypeDivision(@PathVariable int id,
                                                               @RequestParam String title,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByTypeDivision(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники дивизии по категории")
    @GetMapping("/by/category/division/{id}")
    public Page<EquipmentResponse> getEquipmentsByCategoryDivision(@PathVariable int id,
                                                                   @RequestParam String title,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByCategoryDivision(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники бригады по виду")
    @GetMapping("/by/type/brigade/{id}")
    public Page<EquipmentResponse> getEquipmentsByTypeBrigade(@PathVariable int id,
                                                              @RequestParam String title,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByTypeBrigade(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники бригады по категории")
    @GetMapping("/by/category/brigade/{id}")
    public Page<EquipmentResponse> getEquipmentsByCategoryBrigade(@PathVariable int id,
                                                                  @RequestParam String title,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByCategoryBrigade(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники корпуса по виду")
    @GetMapping("/by/type/corps/{id}")
    public Page<EquipmentResponse> getEquipmentsByTypeCorps(@PathVariable int id,
                                                            @RequestParam String title,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByTypeCorps(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники корпуса по категории")
    @GetMapping("/by/category/corps/{id}")
    public Page<EquipmentResponse> getEquipmentsByCategoryCorps(@PathVariable int id,
                                                                @RequestParam String title,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByCategoryCorps(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники армии по виду")
    @GetMapping("/by/type/army/{id}")
    public Page<EquipmentResponse> getEquipmentsByTypeArmy(@PathVariable int id,
                                                           @RequestParam String title,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByTypeArmy(title, id, page, size);
    }

    @Operation(summary = "Получить перечень военной техники по категории")
    @GetMapping("/by/category/army/{id}")
    public Page<EquipmentResponse> getEquipmentsByCategoryArmy(@PathVariable int id,
                                                               @RequestParam String title,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipmentsByCategoryArmy(title, id, page, size);
    }
}