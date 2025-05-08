package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsResponse;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.service.infrastructure.CorpsService;
import ru.nsu.peretyatko.validator.infrastructure.CorpsValidator;

import java.util.List;

@Tag(name = "Corps API")
@RestController
@RequestMapping("/corps")
@RequiredArgsConstructor
public class CorpsController {

    private final CorpsService corpsService;

    private final CorpsValidator corpsValidator;

    @Operation(summary = "Получить перечень корпусов")
    @GetMapping
    public List<CorpsResponse> getCorps() {
        return corpsService.getCorps();
    }

    @Operation(summary = "Получить корпус по ID")
    @GetMapping("/{id}")
    public CorpsResponse getCorpsById(@PathVariable int id) {
        return corpsService.getCorps(id);
    }

    @Operation(summary = "Добавить корпус")
    @PostMapping
    public void createCorps(@Valid @RequestBody CorpsPostRequest corpsPostRequest,
                              BindingResult bindingResult) {
        corpsValidator.validate(corpsPostRequest, bindingResult);
        corpsService.createCorps(corpsPostRequest);
    }

    @Operation(summary = "Изменить данные корпуса по ID")
    @PatchMapping("/{id}")
    public void updateCorps(@PathVariable int id,
                              @Valid @RequestBody CorpsPatchRequest corpsPatchRequest,
                              BindingResult bindingResult) {
        corpsValidator.validate(corpsPatchRequest, bindingResult);
        corpsService.updateCorps(id, corpsPatchRequest);
    }

    @Operation(summary = "Удалить корпус по ID")
    @DeleteMapping("/{id}")
    public void deleteCorps(@PathVariable int id) {
        corpsService.deleteCorps(id);
    }

    @Operation(summary = "Получить корпус у которого больше всего военных частей")
    @GetMapping("/with/most/units")
    public CorpsResponse getCorpsWithMostUnits() {
        return corpsService.getCorpsWithMostUnits();
    }

    @Operation(summary = "Получить корпус у которого меньше всего военных частей")
    @GetMapping("/with/fewest/units")
    public CorpsResponse getCorpsWithFewestUnits() {
        return corpsService.getCorpsWithFewestUnits();
    }

}