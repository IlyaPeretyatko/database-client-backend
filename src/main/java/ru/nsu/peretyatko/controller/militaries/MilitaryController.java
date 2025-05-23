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

import java.util.List;

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
    public List<MilitaryResponse> getOfficers() {
        return militaryService.getOfficers();
    }

    @Operation(summary = "Получить сержантский состав")
    @GetMapping("/sergeants")
    public List<MilitaryResponse> getSergeants() {
        return militaryService.getSergeants();
    }

    @Operation(summary = "Получить рядовой состав")
    @GetMapping("/soldiers")
    public List<MilitaryResponse> getSoldiers() {
        return militaryService.getSoldiers();
    }

    @Operation(summary = "Получить офицерский состав по званию")
    @GetMapping("/officers/by/rank")
    public List<MilitaryResponse> getOfficersByRank(@RequestParam String title) {
        return militaryService.getOfficersByRank(title);
    }

    @Operation(summary = "Получить сержантский состав по званию")
    @GetMapping("/sergeants/by/rank")
    public List<MilitaryResponse> getSergeantsByRank(@RequestParam String title) {
        return militaryService.getSergeantsByRank(title);
    }

    @Operation(summary = "Получить рядовой состав по званию")
    @GetMapping("/soldiers/by/rank")
    public List<MilitaryResponse> getSoldiersByRank(@RequestParam String title) {
        return militaryService.getSoldiersByRank(title);
    }

    @Operation(summary = "Получить офицерский состав военной части по званию")
    @GetMapping("/officers/by/rank/unit/{id}")
    public List<MilitaryResponse> getOfficersUnitByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getOfficersUnitByRank(id, title);
    }

    @Operation(summary = "Получить сержантский состав военной части по званию")
    @GetMapping("/sergeants/by/rank/unit/{id}")
    public List<MilitaryResponse> getSergeantsUnitByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSergeantsUnitByRank(id, title);
    }

    @Operation(summary = "Получить рядовой состав военной части по званию")
    @GetMapping("/soldiers/by/rank/unit/{id}")
    public List<MilitaryResponse> getSoldiersUnitByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSoldiersUnitByRank(id, title);
    }

    @Operation(summary = "Получить офицерский состав дивизии по званию")
    @GetMapping("/officers/by/rank/division/{id}")
    public List<MilitaryResponse> getOfficersDivisionByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getOfficersDivisionByRank(id, title);
    }

    @Operation(summary = "Получить сержантский состав дивизии по званию")
    @GetMapping("/sergeants/by/rank/division/{id}")
    public List<MilitaryResponse> getSergeantsDivisionByRank(@PathVariable int id,
                                                         @RequestParam String title) {
        return militaryService.getSergeantsDivisionByRank(id, title);
    }

    @Operation(summary = "Получить рядовой состав дивизии по званию")
    @GetMapping("/soldiers/by/rank/division/{id}")
    public List<MilitaryResponse> getSoldiersDivisionByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSoldiersDivisionByRank(id, title);
    }

    @Operation(summary = "Получить офицерский состав бригады по званию")
    @GetMapping("/officers/by/rank/brigade/{id}")
    public List<MilitaryResponse> getOfficersBrigadeByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getOfficersBrigadeByRank(id, title);
    }

    @Operation(summary = "Получить сержантский состав бригады по званию")
    @GetMapping("/sergeants/by/rank/brigade/{id}")
    public List<MilitaryResponse> getSergeantsBrigadeByRank(@PathVariable int id,
                                                         @RequestParam String title) {
        return militaryService.getSergeantsBrigadeByRank(id, title);
    }

    @Operation(summary = "Получить рядовой состав бригады по званию")
    @GetMapping("/soldiers/by/rank/brigade/{id}")
    public List<MilitaryResponse> getSoldiersBrigadeByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSoldiersBrigadeByRank(id, title);
    }

    @Operation(summary = "Получить офицерский состав корпуса по званию")
    @GetMapping("/officers/by/rank/corps/{id}")
    public List<MilitaryResponse> getOfficersCorpsByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getOfficersCorpsByRank(id, title);
    }

    @Operation(summary = "Получить сержантский состав корпуса по званию")
    @GetMapping("/sergeants/by/rank/corps/{id}")
    public List<MilitaryResponse> getSergeantsCorpsByRank(@PathVariable int id,
                                                            @RequestParam String rankTitle) {
        return militaryService.getSergeantsCorpsByRank(id, rankTitle);
    }

    @Operation(summary = "Получить рядовой состав корпуса по званию")
    @GetMapping("/soldiers/by/rank/corps/{id}")
    public List<MilitaryResponse> getSoldiersCorpsByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getSoldiersCorpsByRank(id, title);
    }

    @Operation(summary = "Получить офицерский состав армии по званию")
    @GetMapping("/officers/by/rank/army/{id}")
    public List<MilitaryResponse> getOfficersArmyByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getOfficersArmyByRank(id, title);
    }

    @Operation(summary = "Получить сержантский состав армии по званию")
    @GetMapping("/sergeants/by/rank/army/{id}")
    public List<MilitaryResponse> getSergeantsArmyByRank(@PathVariable int id,
                                                            @RequestParam String title) {
        return militaryService.getSergeantsArmyByRank(id, title);
    }

    @Operation(summary = "Получить рядовой состав армии по званию")
    @GetMapping("/soldiers/by/rank/army/{id}")
    public List<MilitaryResponse> getSoldiersArmyByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getSoldiersArmyByRank(id, title);
    }

    @Operation(summary = "Получить военнослужащих военной части по специальности")
    @GetMapping("/by/specialty/unit/{id}")
    public List<MilitaryResponse> getMilitariesBySpecialtyUnit(@PathVariable int id,
                                                               @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyUnit(title, id);
    }

    @Operation(summary = "Получить военнослужащих дивизии по специальности")
    @GetMapping("/by/specialty/division/{id}")
    public List<MilitaryResponse> getMilitariesBySpecialtyDivision(@PathVariable int id,
                                                                   @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyDivision(title, id);
    }

    @Operation(summary = "Получить военнослужащих бригады по специальности")
    @GetMapping("/by/specialty/brigade/{id}")
    public List<MilitaryResponse> getMilitariesBySpecialtyBrigade(@PathVariable int id,
                                                                  @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyBrigade(title, id);
    }

    @Operation(summary = "Получить военнослужащих корпуса по специальности")
    @GetMapping("/by/specialty/corps/{id}")
    public List<MilitaryResponse> getMilitariesBySpecialtyCorps(@PathVariable int id,
                                                                @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyCorps(title, id);
    }

    @Operation(summary = "Получить военнослужащих армии по специальности")
    @GetMapping("/by/specialty/army/{id}")
    public List<MilitaryResponse> getMilitariesBySpecialtyArmy(@PathVariable int id,
                                                               @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyArmy(title, id);
    }

}
