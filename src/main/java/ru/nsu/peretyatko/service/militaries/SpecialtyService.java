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
    public List<SpecialtyResponse> getSpecialtiesInDivision(int divisionId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInDivision(divisionId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInDivision(int divisionId) {
        return specialtyCustomRepository.findNoSpecialtiesInDivision(divisionId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialtiesInBrigade(int brigadeId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInBrigade(brigadeId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInBrigade(int brigadeId) {
        return specialtyCustomRepository.findNoSpecialtiesInBrigade(brigadeId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialtiesInCorps(int corpsId, int minCount) {
        return specialtyCustomRepository.findSpecialtiesInCorps(corpsId, minCount).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getNoSpecialtiesInCorps(int corpsId) {
        return specialtyCustomRepository.findNoSpecialtiesInCorps(corpsId).stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }


}
