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
    public List<MilitaryResponse> getOfficersByRank(@RequestParam String rankTitle) {
        return militaryService.getOfficersByRank(rankTitle);
    }

    @GetMapping("/sergeants/by/rank")
    public List<MilitaryResponse> getSergeantsByRank(@RequestParam String rankTitle) {
        return militaryService.getSergeantsByRank(rankTitle);
    }

    @GetMapping("/soldiers/by/rank")
    public List<MilitaryResponse> getSoldiersByRank(@RequestParam String rankTitle) {
        return militaryService.getSoldiersByRank(rankTitle);
    }

    @GetMapping("/officers/unit/{id}/by/rank")
    public List<MilitaryResponse> getOfficersUnitByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getOfficersUnitByRank(id, rankTitle);
    }

    @GetMapping("/sergeants/unit/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsUnitByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getSergeantsUnitByRank(id, rankTitle);
    }

    @GetMapping("/soldiers/unit/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersUnitByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getSoldiersUnitByRank(id, rankTitle);
    }

    @GetMapping("/officers/division/{id}/by/rank")
    public List<MilitaryResponse> getOfficersDivisionByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getOfficersDivisionByRank(id, rankTitle);
    }

    @GetMapping("/sergeants/division/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsDivisionByRank(@PathVariable int id,
                                                         @RequestParam String rankTitle) {
        return militaryService.getSergeantsDivisionByRank(id, rankTitle);
    }

    @GetMapping("/soldiers/division/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersDivisionByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getSoldiersDivisionByRank(id, rankTitle);
    }

    @GetMapping("/officers/brigade/{id}/by/rank")
    public List<MilitaryResponse> getOfficersBrigadeByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getOfficersBrigadeByRank(id, rankTitle);
    }

    @GetMapping("/sergeants/brigade/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsBrigadeByRank(@PathVariable int id,
                                                         @RequestParam String rankTitle) {
        return militaryService.getSergeantsBrigadeByRank(id, rankTitle);
    }

    @GetMapping("/soldiers/brigade/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersBrigadeByRank(@PathVariable int id,
                                                        @RequestParam String rankTitle) {
        return militaryService.getSoldiersBrigadeByRank(id, rankTitle);
    }

    @GetMapping("/officers/corps/{id}/by/rank")
    public List<MilitaryResponse> getOfficersCorpsByRank(@PathVariable int id,
                                                           @RequestParam String rankTitle) {
        return militaryService.getOfficersCorpsByRank(id, rankTitle);
    }

    @GetMapping("/sergeants/corps/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsCorpsByRank(@PathVariable int id,
                                                            @RequestParam String rankTitle) {
        return militaryService.getSergeantsCorpsByRank(id, rankTitle);
    }

    @GetMapping("/soldiers/corps/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersCorpsByRank(@PathVariable int id,
                                                           @RequestParam String rankTitle) {
        return militaryService.getSoldiersCorpsByRank(id, rankTitle);
    }

    @GetMapping("/officers/army/{id}/by/rank")
    public List<MilitaryResponse> getOfficersArmyByRank(@PathVariable int id,
                                                           @RequestParam String rankTitle) {
        return militaryService.getOfficersArmyByRank(id, rankTitle);
    }

    @GetMapping("/sergeants/army/{id}/by/rank")
    public List<MilitaryResponse> getSergeantsArmyByRank(@PathVariable int id,
                                                            @RequestParam String rankTitle) {
        return militaryService.getSergeantsArmyByRank(id, rankTitle);
    }

    @GetMapping("/soldiers/army/{id}/by/rank")
    public List<MilitaryResponse> getSoldiersArmyByRank(@PathVariable int id,
                                                           @RequestParam String rankTitle) {
        return militaryService.getSoldiersArmyByRank(id, rankTitle);
    }
}
