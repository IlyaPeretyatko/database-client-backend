package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.DivisionMapper;
import ru.nsu.peretyatko.model.infrastructure.Division;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.repository.infrastructure.DivisionCustomRepository;
import ru.nsu.peretyatko.repository.infrastructure.DivisionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionService {

    private final DivisionCustomRepository divisionCustomRepository;

    private final DivisionRepository divisionRepository;

    private final DivisionMapper divisionMapper;

    @Transactional(readOnly = true)
    public Page<DivisionResponse> getDivisions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return divisionRepository.findAll(pageable).map(divisionMapper::toDivisionResponse);
    }

    @Transactional(readOnly = true)
    public DivisionResponse getDivision(int id) {
        Division division = divisionRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Division was not found."));
        return divisionMapper.toDivisionResponse(division);
    }

    @Transactional
    public void createDivision(DivisionPostRequest divisionPostRequest) {
        divisionRepository.save(divisionMapper.toDivision(divisionPostRequest));
    }

    @Transactional
    public void updateDivision(int id, DivisionPatchRequest divisionPatchRequest) {
        Division division = divisionRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Division was not found."));
        divisionMapper.updateDivision(division, divisionPatchRequest);
        divisionRepository.save(division);
    }

    @Transactional
    public void deleteDivision(int id) {
        if (!divisionRepository.existsById(id)) {
            throw new ServiceException(404, "Division was not found.");
        }
        divisionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public DivisionResponse getDivisionWithMostUnits() {
        return divisionMapper.toDivisionResponse(divisionCustomRepository.findDivisionWithMostUnits());
    }

    @Transactional(readOnly = true)
    public DivisionResponse getDivisionWithFewestUnits() {
        return divisionMapper.toDivisionResponse(divisionCustomRepository.findDivisionWithFewestUnits());
    }


}