package ru.nsu.peretyatko.controller.militaries;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.MilitaryPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPostRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryResponse;
import ru.nsu.peretyatko.service.militaries.MilitaryService;
import ru.nsu.peretyatko.validator.militaries.MilitaryValidator;

import java.util.List;

@RestController
@RequestMapping("/militaries")
@RequiredArgsConstructor
public class MilitaryController {

    private final MilitaryService militaryService;

    private final MilitaryValidator militaryValidator;

    @GetMapping
    public List<MilitaryResponse> getMilitaries() {
        return militaryService.getMilitaries();
    }

    @GetMapping("/{id}")
    public MilitaryResponse getMilitaryById(@PathVariable int id) {
        return militaryService.getMilitary(id);
    }

    @PostMapping
    public void createMilitary(@Valid @RequestBody MilitaryPostRequest militaryPostRequest,
                               BindingResult bindingResult) {
        militaryValidator.validate(militaryPostRequest, bindingResult);
        militaryService.createMilitary(militaryPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateMilitary(@PathVariable int id,
                               @Valid @RequestBody MilitaryPatchRequest militaryPatchRequest,
                               BindingResult bindingResult) {
        militaryValidator.validate(militaryPatchRequest, bindingResult);
        militaryService.updateMilitary(id, militaryPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMilitary(@PathVariable int id) {
        militaryService.deleteMilitary(id);
    }
}
