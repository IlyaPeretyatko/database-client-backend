package ru.nsu.peretyatko.mapper.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyResponse;
import ru.nsu.peretyatko.dto.militaries.MilitaryRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryResponse;
import ru.nsu.peretyatko.dto.militaries.SpecialtyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.MilitaryProperty;
import ru.nsu.peretyatko.model.militaries.Rank;
import ru.nsu.peretyatko.model.militaries.Specialty;
import ru.nsu.peretyatko.repository.militaries.RankRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MilitaryMapper {

    private final RankRepository rankRepository;

    private final MilitaryPropertyMapper militaryPropertyMapper;

    private final SpecialtyMapper specialtyMapper;

    public Military toMilitary(MilitaryRequest militaryRequest) {
        Military military = new Military();
        military.setFirstName(militaryRequest.getFirstName());
        military.setLastName(militaryRequest.getLastName());
        military.setMiddleName(militaryRequest.getMiddleName());
        military.setBirthDate(militaryRequest.getBirthDate());
        Rank rank = rankRepository.findById(militaryRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
        military.setRank(rank);
        Set<MilitaryProperty> properties = militaryRequest.getProperties().stream().map(militaryPropertyMapper::toMilitaryProperty).collect(Collectors.toSet());
        military.setProperties(properties);
        Set<Specialty> specialties = militaryRequest.getSpecialties().stream().map(specialtyMapper::toSpecialty).collect(Collectors.toSet());
        military.setSpecialties(specialties);
        return military;
    }

    public MilitaryResponse toMilitaryResponse(Military military) {
        MilitaryResponse militaryResponse = new MilitaryResponse();
        militaryResponse.setId(military.getId());
        militaryResponse.setFirstName(military.getFirstName());
        militaryResponse.setLastName(military.getLastName());
        militaryResponse.setMiddleName(military.getMiddleName());
        militaryResponse.setBirthDate(military.getBirthDate());
        Set<MilitaryPropertyResponse> properties = military.getProperties().stream().map(militaryPropertyMapper::toMilitaryPropertyResponse).collect(Collectors.toSet());
        militaryResponse.setProperties(properties);
        Set<SpecialtyResponse> specialties = military.getSpecialties().stream().map(specialtyMapper::toSpecialtyResponse).collect(Collectors.toSet());
        militaryResponse.setSpecialties(specialties);
        return militaryResponse;
    }

    public void updateMilitary(Military military, MilitaryRequest militaryRequest) {
        if (militaryRequest.getFirstName() != null) {
            military.setFirstName(militaryRequest.getFirstName());
        }
        if (militaryRequest.getLastName() != null) {
            military.setLastName(militaryRequest.getLastName());
        }
        if (militaryRequest.getMiddleName() != null) {
            military.setMiddleName(militaryRequest.getMiddleName());
        }
        if (militaryRequest.getBirthDate() != null) {
            military.setBirthDate(militaryRequest.getBirthDate());
        }
        if (militaryRequest.getRankId() != null) {
            Rank rank = rankRepository.findById(militaryRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
            military.setRank(rank);
        }
        if (militaryRequest.getProperties() != null) {
            Set<MilitaryProperty> properties = militaryRequest.getProperties().stream().map(militaryPropertyMapper::toMilitaryProperty).collect(Collectors.toSet());
            military.setProperties(properties);
        }
        if (militaryRequest.getSpecialties() != null) {
            Set<Specialty> specialties = militaryRequest.getSpecialties().stream().map(specialtyMapper::toSpecialty).collect(Collectors.toSet());
            military.setSpecialties(specialties);
        }
    }

}
