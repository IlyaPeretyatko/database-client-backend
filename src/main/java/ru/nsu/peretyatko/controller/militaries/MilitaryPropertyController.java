package ru.nsu.peretyatko.controller.militaries;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyResponse;
import ru.nsu.peretyatko.service.militaries.MilitaryPropertyService;
import ru.nsu.peretyatko.validator.militaries.MilitaryPropertyValidator;

import java.util.List;

@RestController
@RequestMapping("/api/military/property")
@RequiredArgsConstructor
public class MilitaryPropertyController {

    private final MilitaryPropertyService militaryPropertyService;

    private final MilitaryPropertyValidator militaryPropertyValidator;

    @GetMapping
    public List<MilitaryPropertyResponse> getMilitaryProperties() {
        return militaryPropertyService.getMilitaryProperties();
    }

    @GetMapping("/{id}")
    public MilitaryPropertyResponse getMilitaryPropertyById(@PathVariable int id) {
        return militaryPropertyService.getMilitaryProperty(id);
    }

    @PostMapping
    public void createMilitaryProperty(@Valid @RequestBody MilitaryPropertyRequest militaryPropertyRequest,
                                       BindingResult bindingResult) {
        militaryPropertyValidator.validate(militaryPropertyRequest, bindingResult);
        militaryPropertyService.createMilitaryProperty(militaryPropertyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMilitaryProperty(@PathVariable int id) {
        militaryPropertyService.deleteMilitaryProperty(id);
    }
}
