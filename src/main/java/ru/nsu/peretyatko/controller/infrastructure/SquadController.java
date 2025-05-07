package ru.nsu.peretyatko.controller.infrastructure;

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

    @GetMapping
    public List<SquadResponse> getSquads() {
        return squadService.getSquads();
    }

    @GetMapping("/{id}")
    public SquadResponse getSquadById(@PathVariable int id) {
        return squadService.getSquad(id);
    }

    @PostMapping
    public void createSquad(@Valid @RequestBody SquadPostRequest squadPostRequest,
                              BindingResult bindingResult) {
        squadValidator.validate(squadPostRequest, bindingResult);
        squadService.createSquad(squadPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateSquad(@PathVariable int id,
                              @Valid @RequestBody SquadPatchRequest squadPatchRequest,
                              BindingResult bindingResult) {
        squadValidator.validate(squadPatchRequest, bindingResult);
        squadService.updateSquad(id, squadPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteSquad(@PathVariable int id) {
        squadService.deleteSquad(id);
    }
}