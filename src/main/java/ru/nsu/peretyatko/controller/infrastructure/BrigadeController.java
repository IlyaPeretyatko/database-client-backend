package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePostRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadeResponse;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.service.infrastructure.BrigadeService;
import ru.nsu.peretyatko.validator.infrastructure.BrigadeValidator;

import java.util.List;

@Tag(name = "Brigade API")
@RestController
@RequestMapping("/brigades")
@RequiredArgsConstructor
public class BrigadeController {

    private final BrigadeService brigadeService;

    private final BrigadeValidator brigadeValidator;

    @GetMapping
    public List<BrigadeResponse> getBrigades() {
        return brigadeService.getBrigades();
    }

    @GetMapping("/{id}")
    public BrigadeResponse getBrigadeById(@PathVariable int id) {
        return brigadeService.getBrigade(id);
    }

    @PostMapping
    public void createBrigade(@Valid @RequestBody BrigadePostRequest brigadePostRequest,
                              BindingResult bindingResult) {
        brigadeValidator.validate(brigadePostRequest, bindingResult);
        brigadeService.createBrigade(brigadePostRequest);
    }

    @PatchMapping("/{id}")
    public void updateBrigade(@PathVariable int id,
                              @Valid @RequestBody BrigadePatchRequest brigadePatchRequest,
                              BindingResult bindingResult) {
        brigadeValidator.validate(brigadePatchRequest, bindingResult);
        brigadeService.updateBrigade(id, brigadePatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBrigade(@PathVariable int id) {
        brigadeService.deleteBrigade(id);
    }

    @GetMapping("/with/most/units")
    public BrigadeResponse getBrigadeWithMostUnits() {
        return brigadeService.getBrigadeWithMostUnits();
    }

    @GetMapping("/with/fewest/units")
    public BrigadeResponse getBrigadeWithFewestUnits() {
        return brigadeService.getBrigadeWithFewestUnits();
    }
}