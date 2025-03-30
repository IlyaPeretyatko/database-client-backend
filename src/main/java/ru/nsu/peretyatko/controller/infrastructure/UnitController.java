package ru.nsu.peretyatko.controller.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitResponse;
import ru.nsu.peretyatko.service.infrastructure.UnitService;
import ru.nsu.peretyatko.validator.infrastructure.UnitValidator;

import java.util.List;

@RestController
@RequestMapping("/api/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    private final UnitValidator unitValidator;

    @GetMapping
    public List<UnitResponse> getMilitaries() {
        return unitService.getMilitaries();
    }

    @GetMapping("/{id}")
    public UnitResponse getUnitById(@PathVariable int id) {
        return unitService.getUnit(id);
    }

    @PostMapping
    public void createUnit(@Valid @RequestBody UnitPostRequest unitPostRequest,
                               BindingResult bindingResult) {
        unitValidator.validate(unitPostRequest, bindingResult);
        unitService.createUnit(unitPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateUnit(@PathVariable int id,
                               @Valid @RequestBody UnitPatchRequest unitPatchRequest,
                               BindingResult bindingResult) {
        unitValidator.validate(unitPatchRequest, bindingResult);
        unitService.updateUnit(id, unitPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable int id) {
        unitService.deleteUnit(id);
    }
}