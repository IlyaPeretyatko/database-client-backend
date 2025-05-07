package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.mapper.militaries.SpecialtyMapper;
import ru.nsu.peretyatko.repository.militaries.SpecialtyCustomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyCustomRepository specialtyCustomRepository;

    private final SpecialtyMapper specialtyMapper;

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialtiesInUnit(int unitId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInUnit(unitId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInUnit(int unitId) {
        return specialtyCustomRepository.findNoSpecialtiesInUnit(unitId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialtiesInDivision(int unitId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInDivision(unitId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInDivision(int unitId) {
        return specialtyCustomRepository.findNoSpecialtiesInDivision(unitId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialtiesInBrigade(int unitId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInBrigade(unitId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInBrigade(int unitId) {
        return specialtyCustomRepository.findNoSpecialtiesInBrigade(unitId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialtiesInCorps(int unitId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInCorps(unitId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInCorps(int unitId) {
        return specialtyCustomRepository.findNoSpecialtiesInCorps(unitId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }


}
