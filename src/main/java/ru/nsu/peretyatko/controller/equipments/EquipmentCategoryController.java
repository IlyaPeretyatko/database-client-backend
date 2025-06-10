package ru.nsu.peretyatko.controller.equipments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.equipments.EquipmentCategoryRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentCategoryResponse;
import ru.nsu.peretyatko.service.equipments.EquipmentCategoryService;
import ru.nsu.peretyatko.validator.equipments.EquipmentCategoryValidator;

import java.util.List;

@Tag(name = "Equipment API")
@RestController
@RequestMapping("/equipments/categories")
@RequiredArgsConstructor
public class EquipmentCategoryController {

    private final EquipmentCategoryService equipmentCategoryService;

    private final EquipmentCategoryValidator equipmentCategoryValidator;

    @Operation(summary = "Получить перечень категорий военной техники")
    @GetMapping
    public Page<EquipmentCategoryResponse> getEquipmentCategories(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return equipmentCategoryService.getEquipmentCategories(page, size);
    }

    @Operation(summary = "Получить категорию военной техники по ID")
    @GetMapping("/{id}")
    public EquipmentCategoryResponse getEquipmentCategoryById(@PathVariable int id) {
        return equipmentCategoryService.getEquipmentCategory(id);
    }

    @Operation(summary = "Добавить категорию военной техники")
    @PostMapping
    public void createEquipmentCategory(@Valid @RequestBody EquipmentCategoryRequest equipmentCategoryRequest,
                                   BindingResult bindingResult) {
        equipmentCategoryValidator.validate(equipmentCategoryRequest, bindingResult);
        equipmentCategoryService.createEquipmentCategory(equipmentCategoryRequest);
    }

    @Operation(summary = "Удалить категорию военной техники по ID")
    @DeleteMapping("/{id}")
    public void deleteEquipmentCategory(@PathVariable int id) {
        equipmentCategoryService.deleteEquipmentCategory(id);
    }
}
