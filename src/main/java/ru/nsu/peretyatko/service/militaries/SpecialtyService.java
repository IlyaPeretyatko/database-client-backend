package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.mapper.militaries.SpecialtyMapper;
import ru.nsu.peretyatko.repository.militaries.SpecialtyCustomRepository;


@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyCustomRepository specialtyCustomRepository;

    private final SpecialtyMapper specialtyMapper;

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getSpecialtiesInUnit(int unitId, int minCount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findSpecialtiesInUnit(unitId, minCount, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getNoSpecialtiesInUnit(int unitId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findNoSpecialtiesInUnit(unitId, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getSpecialtiesInDivision(int divisionId, int minCount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findSpecialtiesInDivision(divisionId, minCount, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getNoSpecialtiesInDivision(int divisionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findNoSpecialtiesInDivision(divisionId, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getSpecialtiesInBrigade(int brigadeId, int minCount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findSpecialtiesInBrigade(brigadeId, minCount, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getNoSpecialtiesInBrigade(int brigadeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findNoSpecialtiesInBrigade(brigadeId, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getSpecialtiesInCorps(int corpsId, int minCount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findSpecialtiesInCorps(corpsId, minCount, pageable).map(specialtyMapper::toSpecialtyResponse);
    }

    @Transactional(readOnly = true)
    public Page<SpecialtyResponse> getNoSpecialtiesInCorps(int corpsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specialtyCustomRepository.findNoSpecialtiesInCorps(corpsId, pageable).map(specialtyMapper::toSpecialtyResponse);
    }


}
