package ru.nsu.peretyatko.mapper.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.SpecialtyRequest;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.militaries.Specialty;
import ru.nsu.peretyatko.repository.militaries.SpecialtyRepository;

@Component
@RequiredArgsConstructor
public class SpecialtyMapper {

    private final SpecialtyRepository specialtyRepository;

    public Specialty toSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyRepository.findByTitle(specialtyRequest.getTitle()).orElse(new Specialty());
        specialty.setTitle(specialtyRequest.getTitle());
        return specialty;
    }

    public SpecialtyResponse toSpecialtyResponse(Specialty specialty) {
        SpecialtyResponse specialtyResponse = new SpecialtyResponse();
        specialtyResponse.setId(specialty.getId());
        specialtyResponse.setTitle(specialty.getTitle());
        return specialtyResponse;
    }
}
