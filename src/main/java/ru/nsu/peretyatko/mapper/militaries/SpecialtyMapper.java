package ru.nsu.peretyatko.mapper.militaries;

import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.SpecialtyRequest;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.model.militaries.Specialty;

@Component
public class SpecialtyMapper {

    public Specialty toSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = new Specialty();
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
