package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyResponse;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.service.infrastructure.ArmyService;
import ru.nsu.peretyatko.validator.infrastructure.ArmyValidator;

import java.util.List;

@Tag(name = "Army API")
@RestController
@RequestMapping("/armies")
@RequiredArgsConstructor
public class ArmyController {

    private final ArmyService armyService;

    private final ArmyValidator armyValidator;

    @Operation(summary = "Получить перечень армий")
    @GetMapping
    public List<ArmyResponse> getArmies() {
        return armyService.getArmies();
    }

    @Operation(summary = "Получить армию по ID")
    @GetMapping("/{id}")
    public ArmyResponse getArmyById(@PathVariable int id) {
        return armyService.getArmy(id);
    }

    @Operation(summary = "Добавить армию")
    @PostMapping
    public void createArmy(@Valid @RequestBody ArmyPostRequest armyPostRequest,
                              BindingResult bindingResult) {
        armyValidator.validate(armyPostRequest, bindingResult);
        armyService.createArmy(armyPostRequest);
    }

    @Operation(summary = "Изменить данные армии по ID")
    @PatchMapping("/{id}")
    public void updateArmy(@PathVariable int id,
                              @Valid @RequestBody ArmyPatchRequest armyPatchRequest,
                              BindingResult bindingResult) {
        armyValidator.validate(armyPatchRequest, bindingResult);
        armyService.updateArmy(id, armyPatchRequest);
    }

    @Operation(summary = "Удалить армию по ID")
    @DeleteMapping("/{id}")
    public void deleteArmy(@PathVariable int id) {
        armyService.deleteArmy(id);
    }

    @Operation(summary = "Получить армию у которой больше всего военных частей")
    @GetMapping("/with/most/units")
    public ArmyResponse getArmyWithMostUnits() {
        return armyService.getArmyWithMostUnits();
    }

    @Operation(summary = "Получить армию у которой меньше всего военных частей")
    @GetMapping("/with/fewest/units")
    public ArmyResponse getArmyWithFewestUnits() {
        return armyService.getArmyWithFewestUnits();
    }
}