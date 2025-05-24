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
    public Page<MilitaryResponse> getOfficers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategory("Офицерский состав", pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategory("Сержантский состав", pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategory("Рядовой состав", pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getOfficersByRank(String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRank("Офицерский состав", rankTitle, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeantsByRank(String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRank("Сержантский состав", rankTitle, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiersByRank(String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRank("Рядовой состав", rankTitle, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getOfficersUnitByRank(int unitId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankUnit("Офицерский состав", rankTitle, unitId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeantsUnitByRank(int unitId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankUnit("Сержантский состав", rankTitle, unitId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiersUnitByRank(int unitId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankUnit("Рядовой состав", rankTitle, unitId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getOfficersDivisionByRank(int divisionId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankDivision("Офицерский состав", rankTitle, divisionId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeantsDivisionByRank(int divisionId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankDivision("Сержантский состав", rankTitle, divisionId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiersDivisionByRank(int divisionId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankDivision("Рядовой состав", rankTitle, divisionId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getOfficersBrigadeByRank(int brigadeId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankBrigade("Офицерский состав", rankTitle, brigadeId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeantsBrigadeByRank(int brigadeId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankBrigade("Сержантский состав", rankTitle, brigadeId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiersBrigadeByRank(int brigadeId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankBrigade("Рядовой состав", rankTitle, brigadeId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getOfficersCorpsByRank(int corpsId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankCorps("Офицерский состав", rankTitle, corpsId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeantsCorpsByRank(int corpsId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankCorps("Сержантский состав", rankTitle, corpsId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiersCorpsByRank(int corpsId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankCorps("Рядовой состав", rankTitle, corpsId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getOfficersArmyByRank(int armyId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankArmy("Офицерский состав", rankTitle, armyId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSergeantsArmyByRank(int armyId, String rankTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankArmy("Сержантский состав", rankTitle, armyId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getSoldiersArmyByRank(int armyId, String rankTitle,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesByRankCategoryAndRankArmy("Рядовой состав", rankTitle, armyId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getMilitariesBySpecialtyUnit(String titleSpecialty, int unitId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesBySpecialtyUnit(titleSpecialty, unitId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getMilitariesBySpecialtyDivision(String titleSpecialty, int divisionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesBySpecialtyDivision(titleSpecialty, divisionId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getMilitariesBySpecialtyBrigade(String titleSpecialty, int brigadeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesBySpecialtyBrigade(titleSpecialty, brigadeId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getMilitariesBySpecialtyCorps(String titleSpecialty, int corpsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesBySpecialtyCorps(titleSpecialty, corpsId, pageable).map(militaryMapper::toMilitaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryResponse> getMilitariesBySpecialtyArmy(String titleSpecialty, int armyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryCustomRepository.findMilitariesBySpecialtyArmy(titleSpecialty, armyId, pageable).map(militaryMapper::toMilitaryResponse);
    }

}
