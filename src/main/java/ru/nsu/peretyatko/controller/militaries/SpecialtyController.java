package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.service.militaries.SpecialtyService;

import java.util.List;

@Tag(name = "Specialty API")
@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @Operation(summary = "Получить перечень специальностей военной части по которым есть специалисты")
    @GetMapping("/in/unit/{id}")
    public List<SpecialtyResponse> getSpecialtiesInUnit(@PathVariable int id,
                                                        @RequestParam(required = false, defaultValue = "0") int minCount) {
        return specialtyService.getSpecialtiesInUnit(id, minCount);
    }

    @Operation(summary = "Получить перечень специальностей военной части по которым нету специалистов")
    @GetMapping("/not/in/unit/{id}")
    public List<SpecialtyResponse> getNoSpecialtiesInUnit(@PathVariable int id) {
        return specialtyService.getNoSpecialtiesInUnit(id);
    }

    @Operation(summary = "Получить перечень специальностей дивизии по которым есть специалисты")
    @GetMapping("/in/division/{id}")
    public List<SpecialtyResponse> getSpecialtiesInDivision(@PathVariable int id,
                                                            @RequestParam(required = false, defaultValue = "0") int minCount) {
        return specialtyService.getSpecialtiesInDivision(id, minCount);
    }

    @Operation(summary = "Получить перечень специальностей дивизии по которым нету специалистов")
    @GetMapping("/not/in/division/{id}")
    public List<SpecialtyResponse> getNoSpecialtiesInDivision(@PathVariable int id) {
        return specialtyService.getNoSpecialtiesInDivision(id);
    }

    @Operation(summary = "Получить перечень специальностей бригады по которым есть специалисты")
    @GetMapping("/in/brigade/{id}")
    public List<SpecialtyResponse> getSpecialtiesInBrigade(@PathVariable int id,
                                                           @RequestParam(required = false, defaultValue = "0") int minCount) {
        return specialtyService.getSpecialtiesInBrigade(id, minCount);
    }

    @Operation(summary = "Получить перечень специальностей бригады по которым нету специалистов")
    @GetMapping("/not/in/brigade/{id}")
    public List<SpecialtyResponse> getNoSpecialtiesInBrigade(@PathVariable int id) {
        return specialtyService.getNoSpecialtiesInBrigade(id);
    }

    @Operation(summary = "Получить перечень специальностей корпуса по которым есть специалисты")
    @GetMapping("/in/corps/{id}")
    public List<SpecialtyResponse> getSpecialtiesInCorps(@PathVariable int id,
                                                         @RequestParam(required = false, defaultValue = "0") int minCount) {
        return specialtyService.getSpecialtiesInCorps(id, minCount);
    }

    @Operation(summary = "Получить перечень специальностей в военной части по которым нету специалистов")
    @GetMapping("/not/in/corps/{id}")
    public List<SpecialtyResponse> getNoSpecialtiesInCorps(@PathVariable int id) {
        return specialtyService.getNoSpecialtiesInCorps(id);
    }

}
