package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.service.infrastructure.DivisionService;
import ru.nsu.peretyatko.validator.infrastructure.DivisionValidator;

import java.util.List;

@Tag(name = "Division API")
@RestController
@RequestMapping("/divisions")
@RequiredArgsConstructor
public class DivisionController {

    private final DivisionService divisionService;

    private final DivisionValidator divisionValidator;

    @Operation(summary = "Получить перечень дивизий")
    @GetMapping
    public List<DivisionResponse> getDivisions() {
        return divisionService.getDivisions();
    }

    @Operation(summary = "Получить дивизию по ID")
    @GetMapping("/{id}")
    public DivisionResponse getDivisionById(@PathVariable int id) {
        return divisionService.getDivision(id);
    }

    @Operation(summary = "Добавить дивизию")
    @PostMapping
    public void createDivision(@Valid @RequestBody DivisionPostRequest divisionPostRequest,
                              BindingResult bindingResult) {
        divisionValidator.validate(divisionPostRequest, bindingResult);
        divisionService.createDivision(divisionPostRequest);
    }

    @Operation(summary = "Изменить данные дивизии по ID")
    @PatchMapping("/{id}")
    public void updateDivision(@PathVariable int id,
                              @Valid @RequestBody DivisionPatchRequest divisionPatchRequest,
                              BindingResult bindingResult) {
        divisionValidator.validate(divisionPatchRequest, bindingResult);
        divisionService.updateDivision(id, divisionPatchRequest);
    }

    @Operation(summary = "Удалить дивизию по ID")
    @DeleteMapping("/{id}")
    public void deleteDivision(@PathVariable int id) {
        divisionService.deleteDivision(id);
    }

    @Operation(summary = "Получить дивизию у которой больше всего военных частей")
    @GetMapping("/with/most/units")
    public DivisionResponse getDivisionWithMostUnits() {
        return divisionService.getDivisionWithMostUnits();
    }

    @Operation(summary = "Получить дивизию у которой меньше всего военных частей")
    @GetMapping("/with/fewest/units")
    public DivisionResponse getDivisionWithFewestUnits() {
        return divisionService.getDivisionWithFewestUnits();
    }
}