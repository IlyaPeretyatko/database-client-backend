package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.MilitaryPropertyMapper;
import ru.nsu.peretyatko.model.militaries.MilitaryProperty;
import ru.nsu.peretyatko.repository.militaries.MilitaryPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilitaryPropertyService {

    private final MilitaryPropertyRepository militaryPropertyRepository;

    private final MilitaryPropertyMapper militaryPropertyMapper;

    @Transactional(readOnly = true)
    public List<MilitaryPropertyResponse> getMilitaryProperties() {
        return militaryPropertyRepository.findAll().stream().map(militaryPropertyMapper::toMilitaryPropertyResponse).toList();
    }

    @Transactional(readOnly = true)
    public MilitaryPropertyResponse getMilitaryProperty(int id) {
        MilitaryProperty militaryProperty = militaryPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military property was mot found."));
        return militaryPropertyMapper.toMilitaryPropertyResponse(militaryProperty);
    }

    @Transactional
    public void createMilitaryProperty(MilitaryPropertyRequest militaryPropertyRequest) {
        militaryPropertyRepository.save(militaryPropertyMapper.toMilitaryProperty(militaryPropertyRequest));
    }

    @Transactional
    public void deleteMilitaryProperty(int id) {
        if (!militaryPropertyRepository.existsById(id)) { throw new ServiceException(404, "Military property was mot found."); }
        militaryPropertyRepository.deleteById(id);
    }
}
