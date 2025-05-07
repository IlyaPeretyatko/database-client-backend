package ru.nsu.peretyatko.controller.infrastructure;

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

@RestController
@RequestMapping("/armies")
@RequiredArgsConstructor
public class ArmyController {

    private final ArmyService armyService;

    private final ArmyValidator armyValidator;

    @GetMapping
    public List<ArmyResponse> getArmies() {
        return armyService.getArmies();
    }

    @GetMapping("/{id}")
    public ArmyResponse getArmyById(@PathVariable int id) {
        return armyService.getArmy(id);
    }

    @PostMapping
    public void createArmy(@Valid @RequestBody ArmyPostRequest armyPostRequest,
                              BindingResult bindingResult) {
        armyValidator.validate(armyPostRequest, bindingResult);
        armyService.createArmy(armyPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateArmy(@PathVariable int id,
                              @Valid @RequestBody ArmyPatchRequest armyPatchRequest,
                              BindingResult bindingResult) {
        armyValidator.validate(armyPatchRequest, bindingResult);
        armyService.updateArmy(id, armyPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteArmy(@PathVariable int id) {
        armyService.deleteArmy(id);
    }

    @GetMapping("/with/most/units")
    public ArmyResponse getArmyWithMostUnits() {
        return armyService.getArmyWithMostUnits();
    }

    @GetMapping("/with/fewest/units")
    public ArmyResponse getArmyWithFewestUnits() {
        return armyService.getArmyWithFewestUnits();
    }
}