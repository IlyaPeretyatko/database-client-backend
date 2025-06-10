package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPostRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyResponse;
import ru.nsu.peretyatko.service.militaries.MilitaryPropertyService;
import ru.nsu.peretyatko.validator.militaries.MilitaryPropertyValidator;

import java.util.List;

@Tag(name = "Military API")
@RestController
@RequestMapping("/militaries/properties")
@RequiredArgsConstructor
public class MilitaryPropertyController {

    private final MilitaryPropertyService militaryPropertyService;

    private final MilitaryPropertyValidator militaryPropertyValidator;

    @Operation(summary = "Получить перечень атрибутов военнослужащих")
    @GetMapping
    public Page<MilitaryPropertyResponse> getMilitaryProperties(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return militaryPropertyService.getMilitaryProperties(page, size);
    }

    @Operation(summary = "Получить атрибут военнослужащего по ID")
    @GetMapping("/{id}")
    public MilitaryPropertyResponse getMilitaryPropertyById(@PathVariable int id) {
        return militaryPropertyService.getMilitaryProperty(id);
    }

    @Operation(summary = "Добавить атрибут военнослужащего")
    @PostMapping
    public void createMilitaryProperty(@Valid @RequestBody MilitaryPropertyPostRequest militaryPropertyPostRequest,
                                       BindingResult bindingResult) {
        militaryPropertyValidator.validate(militaryPropertyPostRequest, bindingResult);
        militaryPropertyService.createMilitaryProperty(militaryPropertyPostRequest);
    }

    @Operation(summary = "Изменить данные атрибута военнослужащего по ID")
    @PatchMapping("/{id}")
    public void updateMilitaryProperty(@PathVariable int id,
                               @Valid @RequestBody MilitaryPropertyPatchRequest militaryPropertyPatchRequest,
                               BindingResult bindingResult) {
        militaryPropertyValidator.validate(militaryPropertyPatchRequest, bindingResult);
        militaryPropertyService.updateMilitaryProperty(id, militaryPropertyPatchRequest);
    }

    @Operation(summary = "Удалить атрибут военнослужащего по ID")
    @DeleteMapping("/{id}")
    public void deleteMilitaryProperty(@PathVariable int id) {
        militaryPropertyService.deleteMilitaryProperty(id);
    }
}
