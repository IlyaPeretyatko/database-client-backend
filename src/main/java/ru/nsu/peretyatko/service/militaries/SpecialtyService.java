package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.SpecialtyRequest;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.SpecialtyMapper;
import ru.nsu.peretyatko.model.militaries.Specialty;
import ru.nsu.peretyatko.repository.militaries.SpecialtyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    private final SpecialtyMapper specialtyMapper;

    @Transactional(readOnly = true)
    public List<SpecialtyResponse> getSpecialties() {
        return specialtyRepository.findAll().stream().map(specialtyMapper::toSpecialtyResponse).toList();
    }

    @Transactional(readOnly = true)
    public SpecialtyResponse getSpecialty(int id) {
        Specialty rank = specialtyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Specialty was not found."));
        return specialtyMapper.toSpecialtyResponse(rank);
    }

    @Transactional
    public void createSpecialty(SpecialtyRequest specialtyRequest) {
        specialtyRepository.save(specialtyMapper.toSpecialty(specialtyRequest));
    }

    @Transactional
    public void deleteSpecialty(int id) {
        if (!specialtyRepository.existsById(id)) { throw new ServiceException(404, "Specialty was not found."); }
        specialtyRepository.deleteById(id);
    }
}
