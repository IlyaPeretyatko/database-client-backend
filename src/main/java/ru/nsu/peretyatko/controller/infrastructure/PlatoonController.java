package ru.nsu.peretyatko.controller.infrastructure;

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

@RestController
@RequestMapping("/api/platoon")
@RequiredArgsConstructor
public class PlatoonController {

    private final PlatoonService platoonService;

    private final PlatoonValidator platoonValidator;

    @GetMapping
    public List<PlatoonResponse> getPlatoons() {
        return platoonService.getPlatoons();
    }

    @GetMapping("/{id}")
    public PlatoonResponse getPlatoonById(@PathVariable int id) {
        return platoonService.getPlatoon(id);
    }

    @PostMapping
    public void createPlatoon(@Valid @RequestBody PlatoonPostRequest platoonPostRequest,
                              BindingResult bindingResult) {
        platoonValidator.validate(platoonPostRequest, bindingResult);
        platoonService.createPlatoon(platoonPostRequest);
    }

    @PatchMapping("/{id}")
    public void updatePlatoon(@PathVariable int id,
                              @Valid @RequestBody PlatoonPatchRequest platoonPatchRequest,
                              BindingResult bindingResult) {
        platoonValidator.validate(platoonPatchRequest, bindingResult);
        platoonService.updatePlatoon(id, platoonPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePlatoon(@PathVariable int id) {
        platoonService.deletePlatoon(id);
    }
}