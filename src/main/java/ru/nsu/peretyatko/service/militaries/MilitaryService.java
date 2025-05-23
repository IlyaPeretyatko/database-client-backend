package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.MilitaryPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPostRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.MilitaryMapper;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.militaries.MilitaryCustomRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilitaryService {

    private final MilitaryCustomRepository militaryCustomRepository;

    private final MilitaryRepository militaryRepository;

    private final MilitaryMapper militaryMapper;

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getMilitaries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryRepository.findAll(pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public MilitaryResponse getMilitary(int id) {
        Military military = militaryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military was not found."));
        return militaryMapper.toMilitaryResponse(military);
    }

    @Transactional
    public void createMilitary(MilitaryPostRequest militaryPostRequest) {
        militaryRepository.save(militaryMapper.toMilitary(militaryPostRequest));
    }

    @Transactional
    public void updateMilitary(int id, MilitaryPatchRequest militaryPatchRequest) {
        Military military = militaryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military was not found."));
        militaryMapper.updateMilitary(military, militaryPatchRequest);
        militaryRepository.save(military);
    }

    @Transactional
    public void deleteMilitary(int id) {
        if (!militaryRepository.existsById(id)) {
            throw new ServiceException(404, "Military was not found.");
        }
        militaryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficers() {
        return militaryCustomRepository.findMilitariesByRankCategory("Офицерский состав").stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeants() {
        return militaryCustomRepository.findMilitariesByRankCategory("Сержантский состав").stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiers() {
        return militaryCustomRepository.findMilitariesByRankCategory("Рядовой состав").stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficersByRank(String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRank("Офицерский состав", rankTitle).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeantsByRank(String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRank("Сержантский состав", rankTitle).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiersByRank(String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRank("Рядовой состав", rankTitle).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficersUnitByRank(int unitId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankUnit("Офицерский состав", rankTitle, unitId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeantsUnitByRank(int unitId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankUnit("Сержантский состав", rankTitle, unitId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiersUnitByRank(int unitId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankUnit("Рядовой состав", rankTitle, unitId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficersDivisionByRank(int divisionId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankDivision("Офицерский состав", rankTitle, divisionId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeantsDivisionByRank(int divisionId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankDivision("Сержантский состав", rankTitle, divisionId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiersDivisionByRank(int divisionId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankDivision("Рядовой состав", rankTitle, divisionId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficersBrigadeByRank(int brigadeId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankBrigade("Офицерский состав", rankTitle, brigadeId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeantsBrigadeByRank(int brigadeId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankBrigade("Сержантский состав", rankTitle, brigadeId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiersBrigadeByRank(int brigadeId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankBrigade("Рядовой состав", rankTitle, brigadeId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficersCorpsByRank(int corpsId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankCorps("Офицерский состав", rankTitle, corpsId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeantsCorpsByRank(int corpsId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankCorps("Сержантский состав", rankTitle, corpsId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiersCorpsByRank(int corpsId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankCorps("Рядовой состав", rankTitle, corpsId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getOfficersArmyByRank(int armyId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankArmy("Офицерский состав", rankTitle, armyId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSergeantsArmyByRank(int armyId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankArmy("Сержантский состав", rankTitle, armyId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getSoldiersArmyByRank(int armyId, String rankTitle) {
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankArmy("Рядовой состав", rankTitle, armyId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getMilitariesBySpecialtyUnit(String titleSpecialty, int unitId) {
        return militaryCustomRepository.findMilitariesBySpecialtyUnit(titleSpecialty, unitId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getMilitariesBySpecialtyDivision(String titleSpecialty, int divisionId) {
        return militaryCustomRepository.findMilitariesBySpecialtyDivision(titleSpecialty, divisionId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getMilitariesBySpecialtyBrigade(String titleSpecialty, int brigadeId) {
        return militaryCustomRepository.findMilitariesBySpecialtyBrigade(titleSpecialty, brigadeId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getMilitariesBySpecialtyCorps(String titleSpecialty, int corpsId) {
        return militaryCustomRepository.findMilitariesBySpecialtyCorps(titleSpecialty, corpsId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getMilitariesBySpecialtyArmy(String titleSpecialty, int armyId) {
        return militaryCustomRepository.findMilitariesBySpecialtyArmy(titleSpecialty, armyId).stream().map(militaryMapper::toMilitaryResponse).toList();
    }

}
