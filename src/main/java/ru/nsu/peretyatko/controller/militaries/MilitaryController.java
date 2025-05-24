package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.MilitaryPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPostRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryResponse;
import ru.nsu.peretyatko.service.militaries.MilitaryService;
import ru.nsu.peretyatko.validator.militaries.MilitaryValidator;


@Tag(name = "Military API")
@RestController
@RequestMapping("/militaries")
@RequiredArgsConstructor
public class MilitaryController {

    private final MilitaryService militaryService;

    private final MilitaryValidator militaryValidator;

    @Operation(summary = "Получить перечень военнослужащих")
    @GetMapping
    public Page<MilitaryResponse> getMilitaries(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return militaryService.getMilitaries(page, size);
    }

    @Operation(summary = "Получить военнослужащего по ID")
    @GetMapping("/{id}")
    public MilitaryResponse getMilitaryById(@PathVariable int id) {
        return militaryService.getMilitary(id);
    }

    @Operation(summary = "Добавить военнослужащего")
    @PostMapping
    public void createMilitary(@Valid @RequestBody MilitaryPostRequest militaryPostRequest,
                               BindingResult bindingResult) {
        militaryValidator.validate(militaryPostRequest, bindingResult);
        militaryService.createMilitary(militaryPostRequest);
    }

    @Operation(summary = "Изменить данные военнослужащего по ID")
    @PatchMapping("/{id}")
    public void updateMilitary(@PathVariable int id,
                               @Valid @RequestBody MilitaryPatchRequest militaryPatchRequest,
                               BindingResult bindingResult) {
        militaryValidator.validate(militaryPatchRequest, bindingResult);
        militaryService.updateMilitary(id, militaryPatchRequest);
    }

    @Operation(summary = "Удалить военнослужащего по ID")
    @DeleteMapping("/{id}")
    public void deleteMilitary(@PathVariable int id) {
        militaryService.deleteMilitary(id);
    }

    @Operation(summary = "Получить офицерский состав")
    @GetMapping("/officers")
    public Page<MilitaryResponse> getOfficers(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficers(page, size);
    }

    @Operation(summary = "Получить сержантский состав")
    @GetMapping("/sergeants")
    public Page<MilitaryResponse> getSergeants(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeants(page, size);
    }

    @Operation(summary = "Получить рядовой состав")
    @GetMapping("/soldiers")
    public Page<MilitaryResponse> getSoldiers(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiers(page, size);
    }

    @Operation(summary = "Получить офицерский состав по званию")
    @GetMapping("/officers/by/rank")
    public Page<MilitaryResponse> getOfficersByRank(@RequestParam String title,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficersByRank(title, page, size);
    }

    @Operation(summary = "Получить сержантский состав по званию")
    @GetMapping("/sergeants/by/rank")
    public Page<MilitaryResponse> getSergeantsByRank(@RequestParam String title,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeantsByRank(title, page, size);
    }

    @Operation(summary = "Получить рядовой состав по званию")
    @GetMapping("/soldiers/by/rank")
    public Page<MilitaryResponse> getSoldiersByRank(@RequestParam String title,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiersByRank(title, page, size);
    }

    @Operation(summary = "Получить офицерский состав военной части по званию")
    @GetMapping("/officers/by/rank/unit/{id}")
    public Page<MilitaryResponse> getOfficersUnitByRank(@PathVariable int id,
                                                        @RequestParam String title,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficersUnitByRank(id, title, page, size);
    }

    @Operation(summary = "Получить сержантский состав военной части по званию")
    @GetMapping("/sergeants/by/rank/unit/{id}")
    public Page<MilitaryResponse> getSergeantsUnitByRank(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeantsUnitByRank(id, title, page, size);
    }

    @Operation(summary = "Получить рядовой состав военной части по званию")
    @GetMapping("/soldiers/by/rank/unit/{id}")
    public Page<MilitaryResponse> getSoldiersUnitByRank(@PathVariable int id,
                                                        @RequestParam String title,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiersUnitByRank(id, title, page, size);
    }

    @Operation(summary = "Получить офицерский состав дивизии по званию")
    @GetMapping("/officers/by/rank/division/{id}")
    public Page<MilitaryResponse> getOfficersDivisionByRank(@PathVariable int id,
                                                            @RequestParam String title,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficersDivisionByRank(id, title, page, size);
    }

    @Operation(summary = "Получить сержантский состав дивизии по званию")
    @GetMapping("/sergeants/by/rank/division/{id}")
    public Page<MilitaryResponse> getSergeantsDivisionByRank(@PathVariable int id,
                                                             @RequestParam String title,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeantsDivisionByRank(id, title, page, size);
    }

    @Operation(summary = "Получить рядовой состав дивизии по званию")
    @GetMapping("/soldiers/by/rank/division/{id}")
    public Page<MilitaryResponse> getSoldiersDivisionByRank(@PathVariable int id,
                                                            @RequestParam String title,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiersDivisionByRank(id, title, page, size);
    }

    @Operation(summary = "Получить офицерский состав бригады по званию")
    @GetMapping("/officers/by/rank/brigade/{id}")
    public Page<MilitaryResponse> getOfficersBrigadeByRank(@PathVariable int id,
                                                           @RequestParam String title,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficersBrigadeByRank(id, title, page, size);
    }

    @Operation(summary = "Получить сержантский состав бригады по званию")
    @GetMapping("/sergeants/by/rank/brigade/{id}")
    public Page<MilitaryResponse> getSergeantsBrigadeByRank(@PathVariable int id,
                                                            @RequestParam String title,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeantsBrigadeByRank(id, title, page, size);
    }

    @Operation(summary = "Получить рядовой состав бригады по званию")
    @GetMapping("/soldiers/by/rank/brigade/{id}")
    public Page<MilitaryResponse> getSoldiersBrigadeByRank(@PathVariable int id,
                                                           @RequestParam String title,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiersBrigadeByRank(id, title, page, size);
    }

    @Operation(summary = "Получить офицерский состав корпуса по званию")
    @GetMapping("/officers/by/rank/corps/{id}")
    public Page<MilitaryResponse> getOfficersCorpsByRank(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficersCorpsByRank(id, title, page, size);
    }

    @Operation(summary = "Получить сержантский состав корпуса по званию")
    @GetMapping("/sergeants/by/rank/corps/{id}")
    public Page<MilitaryResponse> getSergeantsCorpsByRank(@PathVariable int id,
                                                          @RequestParam String rankTitle,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeantsCorpsByRank(id, rankTitle, page, size);
    }

    @Operation(summary = "Получить рядовой состав корпуса по званию")
    @GetMapping("/soldiers/by/rank/corps/{id}")
    public Page<MilitaryResponse> getSoldiersCorpsByRank(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiersCorpsByRank(id, title, page, size);
    }

    @Operation(summary = "Получить офицерский состав армии по званию")
    @GetMapping("/officers/by/rank/army/{id}")
    public Page<MilitaryResponse> getOfficersArmyByRank(@PathVariable int id,
                                                        @RequestParam String title,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return militaryService.getOfficersArmyByRank(id, title, page, size);
    }

    @Operation(summary = "Получить сержантский состав армии по званию")
    @GetMapping("/sergeants/by/rank/army/{id}")
    public Page<MilitaryResponse> getSergeantsArmyByRank(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSergeantsArmyByRank(id, title, page, size);
    }

    @Operation(summary = "Получить рядовой состав армии по званию")
    @GetMapping("/soldiers/by/rank/army/{id}")
    public Page<MilitaryResponse> getSoldiersArmyByRank(@PathVariable int id,
                                                        @RequestParam String title,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return militaryService.getSoldiersArmyByRank(id, title, page, size);
    }

    @Operation(summary = "Получить военнослужащих военной части по специальности")
    @GetMapping("/by/specialty/unit/{id}")
    public Page<MilitaryResponse> getMilitariesBySpecialtyUnit(@PathVariable int id,
                                                               @RequestParam String title,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        return militaryService.getMilitariesBySpecialtyUnit(title, id, page, size);
    }

    @Operation(summary = "Получить военнослужащих дивизии по специальности")
    @GetMapping("/by/specialty/division/{id}")
    public Page<MilitaryResponse> getMilitariesBySpecialtyDivision(@PathVariable int id,
                                                                   @RequestParam String title,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return militaryService.getMilitariesBySpecialtyDivision(title, id, page, size);
    }

    @Operation(summary = "Получить военнослужащих бригады по специальности")
    @GetMapping("/by/specialty/brigade/{id}")
    public Page<MilitaryResponse> getMilitariesBySpecialtyBrigade(@PathVariable int id,
                                                                  @RequestParam String title,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return militaryService.getMilitariesBySpecialtyBrigade(title, id, page, size);
    }

    @Operation(summary = "Получить военнослужащих корпуса по специальности")
    @GetMapping("/by/specialty/corps/{id}")
    public Page<MilitaryResponse> getMilitariesBySpecialtyCorps(@PathVariable int id,
                                                                @RequestParam String title,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return militaryService.getMilitariesBySpecialtyCorps(title, id, page, size);
    }

    @Operation(summary = "Получить военнослужащих армии по специальности")
    @GetMapping("/by/specialty/army/{id}")
    public Page<MilitaryResponse> getMilitariesBySpecialtyArmy(@PathVariable int id,
                                                               @RequestParam String title,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        return militaryService.getMilitariesBySpecialtyArmy(title, id, page, size);
    }

}
