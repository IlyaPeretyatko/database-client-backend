package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.SquadPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadResponse;
import ru.nsu.peretyatko.service.infrastructure.SquadService;
import ru.nsu.peretyatko.validator.infrastructure.SquadValidator;

import java.util.List;

@Tag(name = "Separations of units API")
@RestController
@RequestMapping("/squads")
@RequiredArgsConstructor
public class SquadController {

    private final SquadService squadService;

    private final SquadValidator squadValidator;

    @Operation(summary = "Получить перечень отрядов")
    @GetMapping
    public List<SquadResponse> getSquads() {
        return squadService.getSquads();
    }

    @Operation(summary = "Получить отряд по ID")
    @GetMapping("/{id}")
    public SquadResponse getSquadById(@PathVariable int id) {
        return squadService.getSquad(id);
    }

    @Operation(summary = "Добавить отряд")
    @PostMapping
    public void createSquad(@Valid @RequestBody SquadPostRequest squadPostRequest,
                              BindingResult bindingResult) {
        squadValidator.validate(squadPostRequest, bindingResult);
        squadService.createSquad(squadPostRequest);
    }

    @Operation(summary = "Изменить данные отряда по ID")
    @PatchMapping("/{id}")
    public void updateSquad(@PathVariable int id,
                              @Valid @RequestBody SquadPatchRequest squadPatchRequest,
                              BindingResult bindingResult) {
        squadValidator.validate(squadPatchRequest, bindingResult);
        squadService.updateSquad(id, squadPatchRequest);
    }

    @Operation(summary = "Удалить отряд по ID")
    @DeleteMapping("/{id}")
    public void deleteSquad(@PathVariable int id) {
        squadService.deleteSquad(id);
    }
}