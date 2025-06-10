package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.service.militaries.SpecialtyService;


@Tag(name = "Specialty API")
@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @Operation(summary = "Получить перечень специальностей военной части по которым есть специалисты")
    @GetMapping("/in/unit/{id}")
    public Page<SpecialtyResponse> getSpecialtiesInUnit(@PathVariable int id,
                                                        @RequestParam(required = false, defaultValue = "0") int minCount,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getSpecialtiesInUnit(id, minCount, page, size);
    }

    @Operation(summary = "Получить перечень специальностей военной части по которым нету специалистов")
    @GetMapping("/not/in/unit/{id}")
    public Page<SpecialtyResponse> getNoSpecialtiesInUnit(@PathVariable int id,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getNoSpecialtiesInUnit(id, page, size);
    }

    @Operation(summary = "Получить перечень специальностей дивизии по которым есть специалисты")
    @GetMapping("/in/division/{id}")
    public Page<SpecialtyResponse> getSpecialtiesInDivision(@PathVariable int id,
                                                            @RequestParam(required = false, defaultValue = "0") int minCount,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getSpecialtiesInDivision(id, minCount, page, size);
    }

    @Operation(summary = "Получить перечень специальностей дивизии по которым нету специалистов")
    @GetMapping("/not/in/division/{id}")
    public Page<SpecialtyResponse> getNoSpecialtiesInDivision(@PathVariable int id,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getNoSpecialtiesInDivision(id, page, size);
    }

    @Operation(summary = "Получить перечень специальностей бригады по которым есть специалисты")
    @GetMapping("/in/brigade/{id}")
    public Page<SpecialtyResponse> getSpecialtiesInBrigade(@PathVariable int id,
                                                           @RequestParam(required = false, defaultValue = "0") int minCount,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getSpecialtiesInBrigade(id, minCount, page, size);
    }

    @Operation(summary = "Получить перечень специальностей бригады по которым нету специалистов")
    @GetMapping("/not/in/brigade/{id}")
    public Page<SpecialtyResponse> getNoSpecialtiesInBrigade(@PathVariable int id,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getNoSpecialtiesInBrigade(id, page, size);
    }

    @Operation(summary = "Получить перечень специальностей корпуса по которым есть специалисты")
    @GetMapping("/in/corps/{id}")
    public Page<SpecialtyResponse> getSpecialtiesInCorps(@PathVariable int id,
                                                         @RequestParam(required = false, defaultValue = "0") int minCount,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getSpecialtiesInCorps(id, minCount, page, size);
    }

    @Operation(summary = "Получить перечень специальностей в военной части по которым нету специалистов")
    @GetMapping("/not/in/corps/{id}")
    public Page<SpecialtyResponse> getNoSpecialtiesInCorps(@PathVariable int id,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return specialtyService.getNoSpecialtiesInCorps(id, page, size);
    }

}
