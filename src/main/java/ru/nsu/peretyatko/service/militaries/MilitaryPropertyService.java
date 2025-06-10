package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.MilitaryPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPostRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.MilitaryPropertyMapper;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.MilitaryProperty;
import ru.nsu.peretyatko.repository.militaries.MilitaryPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilitaryPropertyService {

    private final MilitaryPropertyRepository militaryPropertyRepository;

    private final MilitaryPropertyMapper militaryPropertyMapper;

    @Transactional(readOnly = true)
    public Page<MilitaryPropertyResponse> getMilitaryProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return militaryPropertyRepository.findAll(pageable).map(militaryPropertyMapper::toMilitaryPropertyResponse);
    }

    @Transactional(readOnly = true)
    public MilitaryPropertyResponse getMilitaryProperty(int id) {
        MilitaryProperty militaryProperty = militaryPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military property was mot found."));
        return militaryPropertyMapper.toMilitaryPropertyResponse(militaryProperty);
    }

    @Transactional
    public void createMilitaryProperty(MilitaryPropertyPostRequest militaryPropertyPostRequest) {
        militaryPropertyRepository.save(militaryPropertyMapper.toMilitaryProperty(militaryPropertyPostRequest));
    }

    @Transactional
    public void updateMilitaryProperty(int id, MilitaryPropertyPatchRequest militaryPropertyPatchRequest) {
        MilitaryProperty militaryProperty = militaryPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military property was not found."));
        militaryPropertyMapper.updateMilitaryProperty(militaryProperty, militaryPropertyPatchRequest);
        militaryPropertyRepository.save(militaryProperty);
    }

    @Transactional
    public void deleteMilitaryProperty(int id) {
        if (!militaryPropertyRepository.existsById(id)) { throw new ServiceException(404, "Military property was mot found."); }
        militaryPropertyRepository.deleteById(id);
    }
}
