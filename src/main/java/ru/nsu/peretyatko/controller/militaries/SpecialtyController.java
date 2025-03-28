package ru.nsu.peretyatko.controller.militaries;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.SpecialtyRequest;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.service.militaries.SpecialtyService;
import ru.nsu.peretyatko.validator.militaries.SpecialtyValidator;

import java.util.List;

@Controller
@RequestMapping("/api/military/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    private final SpecialtyValidator specialtyValidator;

    @GetMapping
    public List<SpecialtyResponse> getSpecialties() {
        return specialtyService.getSpecialties();
    }

    @GetMapping("/{id}")
    public SpecialtyResponse getSpecialty(@PathVariable int id) {
        return specialtyService.getSpecialty(id);
    }

    @PostMapping
    public void createSpecialty(@Valid @RequestBody SpecialtyRequest specialtyRequest,
                                BindingResult bindingResult) {
        specialtyValidator.validate(specialtyRequest, bindingResult);
        specialtyService.createSpecialty(specialtyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialty(@PathVariable int id) {
        specialtyService.deleteSpecialty(id);
    }
}
