package ru.nsu.peretyatko.controller.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsResponse;
import ru.nsu.peretyatko.service.infrastructure.CorpsService;
import ru.nsu.peretyatko.validator.infrastructure.CorpsValidator;

import java.util.List;

@RestController
@RequestMapping("/corps")
@RequiredArgsConstructor
public class CorpsController {

    private final CorpsService corpsService;

    private final CorpsValidator corpsValidator;

    @GetMapping
    public List<CorpsResponse> getCorps() {
        return corpsService.getCorps();
    }

    @GetMapping("/{id}")
    public CorpsResponse getCorpsById(@PathVariable int id) {
        return corpsService.getCorps(id);
    }

    @PostMapping
    public void createCorps(@Valid @RequestBody CorpsPostRequest corpsPostRequest,
                              BindingResult bindingResult) {
        corpsValidator.validate(corpsPostRequest, bindingResult);
        corpsService.createCorps(corpsPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateCorps(@PathVariable int id,
                              @Valid @RequestBody CorpsPatchRequest corpsPatchRequest,
                              BindingResult bindingResult) {
        corpsValidator.validate(corpsPatchRequest, bindingResult);
        corpsService.updateCorps(id, corpsPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCorps(@PathVariable int id) {
        corpsService.deleteCorps(id);
    }
}