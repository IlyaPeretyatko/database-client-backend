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

    @GetMapping("/officers")
    public List<MilitaryResponse> getOfficers() {
        return militaryService.getOfficers();
    }

    @GetMapping("/sergeants")
    public List<MilitaryResponse> getSergeants() {
        return militaryService.getSergeants();
    }

    @GetMapping("/soldiers")
    public List<MilitaryResponse> getSoldiers() {
        return militaryService.getSoldiers();
    }

    @GetMapping("/officers/by/rank")
    public List<MilitaryResponse> getOfficersByRank(@RequestParam String title) {
        return militaryService.getOfficersByRank(title);
    }

    @GetMapping("/sergeants/by/rank")
    public List<MilitaryResponse> getSergeantsByRank(@RequestParam String title) {
        return militaryService.getSergeantsByRank(title);
    }

    @GetMapping("/soldiers/by/rank")
    public List<MilitaryResponse> getSoldiersByRank(@RequestParam String title) {
        return militaryService.getSoldiersByRank(title);
    }

    @GetMapping("/officers/unit/{id}/by/rank")
    public List<MilitaryResponse> getOfficersUnitByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getOfficersUnitByRank(id, title);
    }

    @GetMapping("/sergeants/unit/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsUnitByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSergeantsUnitByRank(id, title);
    }

    @GetMapping("/soldiers/unit/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersUnitByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSoldiersUnitByRank(id, title);
    }

    @GetMapping("/officers/division/{id}/by/rank")
    public List<MilitaryResponse> getOfficersDivisionByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getOfficersDivisionByRank(id, title);
    }

    @GetMapping("/sergeants/division/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsDivisionByRank(@PathVariable int id,
                                                         @RequestParam String title) {
        return militaryService.getSergeantsDivisionByRank(id, title);
    }

    @GetMapping("/soldiers/division/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersDivisionByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSoldiersDivisionByRank(id, title);
    }

    @GetMapping("/officers/brigade/{id}/by/rank")
    public List<MilitaryResponse> getOfficersBrigadeByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getOfficersBrigadeByRank(id, title);
    }

    @GetMapping("/sergeants/brigade/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsBrigadeByRank(@PathVariable int id,
                                                         @RequestParam String title) {
        return militaryService.getSergeantsBrigadeByRank(id, title);
    }

    @GetMapping("/soldiers/brigade/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersBrigadeByRank(@PathVariable int id,
                                                        @RequestParam String title) {
        return militaryService.getSoldiersBrigadeByRank(id, title);
    }

    @GetMapping("/officers/corps/{id}/by/rank")
    public List<MilitaryResponse> getOfficersCorpsByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getOfficersCorpsByRank(id, title);
    }

    @GetMapping("/sergeants/corps/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsCorpsByRank(@PathVariable int id,
                                                            @RequestParam String rankTitle) {
        return militaryService.getSergeantsCorpsByRank(id, rankTitle);
    }

    @GetMapping("/soldiers/corps/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersCorpsByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getSoldiersCorpsByRank(id, title);
    }

    @GetMapping("/officers/army/{id}/by/rank")
    public List<MilitaryResponse> getOfficersArmyByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getOfficersArmyByRank(id, title);
    }

    @GetMapping("/sergeants/army/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsArmyByRank(@PathVariable int id,
                                                            @RequestParam String title) {
        return militaryService.getSergeantsArmyByRank(id, title);
    }

    @GetMapping("/soldiers/army/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersArmyByRank(@PathVariable int id,
                                                           @RequestParam String title) {
        return militaryService.getSoldiersArmyByRank(id, title);
    }

    @GetMapping("/unit/{id}/by/specialty")
    public List<MilitaryResponse> getMilitariesBySpecialtyUnit(@PathVariable int id,
                                                               @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyUnit(title, id);
    }

    @GetMapping("/division/{id}/by/specialty")
    public List<MilitaryResponse> getMilitariesBySpecialtyDivision(@PathVariable int id,
                                                                   @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyDivision(title, id);
    }

    @GetMapping("/brigade/{id}/by/specialty")
    public List<MilitaryResponse> getMilitariesBySpecialtyBrigade(@PathVariable int id,
                                                                  @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyBrigade(title, id);
    }

    @GetMapping("/corps/{id}/by/specialty")
    public List<MilitaryResponse> getMilitariesBySpecialtyCorps(@PathVariable int id,
                                                                @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyCorps(title, id);
    }

    @GetMapping("/army/{id}/by/specialty")
    public List<MilitaryResponse> getMilitariesBySpecialtyArmy(@PathVariable int id,
                                                               @RequestParam String title) {
        return militaryService.getMilitariesBySpecialtyArmy(title, id);
    }

}
