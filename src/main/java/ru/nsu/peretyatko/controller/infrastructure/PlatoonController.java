package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonResponse;
import ru.nsu.peretyatko.service.infrastructure.PlatoonService;
import ru.nsu.peretyatko.validator.infrastructure.PlatoonValidator;

import java.util.List;

@Tag(name = "Separations of units API")
@RestController
@RequestMapping("/platoons")
@RequiredArgsConstructor
public class PlatoonController {

    private final PlatoonService platoonService;

    private final PlatoonValidator platoonValidator;

    @Operation(summary = "Получить перечень взводов")
    @GetMapping
    public List<PlatoonResponse> getPlatoons() {
        return platoonService.getPlatoons();
    }

    @Operation(summary = "Получить взвод по ID")
    @GetMapping("/{id}")
    public PlatoonResponse getPlatoonById(@PathVariable int id) {
        return platoonService.getPlatoon(id);
    }

    @Operation(summary = "Добавить взвод")
    @PostMapping
    public void createPlatoon(@Valid @RequestBody PlatoonPostRequest platoonPostRequest,
                              BindingResult bindingResult) {
        platoonValidator.validate(platoonPostRequest, bindingResult);
        platoonService.createPlatoon(platoonPostRequest);
    }

    @Operation(summary = "Изменить данные взвода по ID")
    @PatchMapping("/{id}")
    public void updatePlatoon(@PathVariable int id,
                              @Valid @RequestBody PlatoonPatchRequest platoonPatchRequest,
                              BindingResult bindingResult) {
        platoonValidator.validate(platoonPatchRequest, bindingResult);
        platoonService.updatePlatoon(id, platoonPatchRequest);
    }

    @Operation(summary = "Удалить взвод по ID")
    @DeleteMapping("/{id}")
    public void deletePlatoon(@PathVariable int id) {
        platoonService.deletePlatoon(id);
    }
}