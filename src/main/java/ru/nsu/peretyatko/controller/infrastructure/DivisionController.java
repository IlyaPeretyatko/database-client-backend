package ru.nsu.peretyatko.controller.infrastructure;

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

    @GetMapping
    public List<DivisionResponse> getDivisions() {
        return divisionService.getDivisions();
    }

    @GetMapping("/{id}")
    public DivisionResponse getDivisionById(@PathVariable int id) {
        return divisionService.getDivision(id);
    }

    @PostMapping
    public void createDivision(@Valid @RequestBody DivisionPostRequest divisionPostRequest,
                              BindingResult bindingResult) {
        divisionValidator.validate(divisionPostRequest, bindingResult);
        divisionService.createDivision(divisionPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateDivision(@PathVariable int id,
                              @Valid @RequestBody DivisionPatchRequest divisionPatchRequest,
                              BindingResult bindingResult) {
        divisionValidator.validate(divisionPatchRequest, bindingResult);
        divisionService.updateDivision(id, divisionPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteDivision(@PathVariable int id) {
        divisionService.deleteDivision(id);
    }

    @GetMapping("/with/most/units")
    public DivisionResponse getDivisionWithMostUnits() {
        return divisionService.getDivisionWithMostUnits();
    }

    @GetMapping("/with/fewest/units")
    public DivisionResponse getDivisionWithFewestUnits() {
        return divisionService.getDivisionWithFewestUnits();
    }
}