package ru.nsu.peretyatko.mapper.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.*;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.Rank;
import ru.nsu.peretyatko.model.militaries.Specialty;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;
import ru.nsu.peretyatko.repository.militaries.RankRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MilitaryMapper {

    private final RankRepository rankRepository;

    private final UnitRepository unitRepository;

    private final MilitaryPropertyMapper militaryPropertyMapper;

    private final SpecialtyMapper specialtyMapper;

    public Military toMilitary(MilitaryPostRequest militaryPostRequest) {
        Military military = new Military();
        military.setFirstName(militaryPostRequest.getFirstName());
        military.setLastName(militaryPostRequest.getLastName());
        military.setMiddleName(militaryPostRequest.getMiddleName());
        military.setBirthDate(militaryPostRequest.getBirthDate());
        Rank rank = rankRepository.findById(militaryPostRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
        military.setRank(rank);
        if (militaryPostRequest.getUnitId() != null) {
            Unit unit = unitRepository.findById(militaryPostRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
            military.setUnit(unit);
        }
        Set<Specialty> specialties = militaryPostRequest.getSpecialties().stream().map(specialtyMapper::toSpecialty).collect(Collectors.toSet());
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
        militaryResponse.setRankId(military.getRank().getId());
        militaryResponse.setUnitId(military.getUnit().getId());
        Set<MilitaryPropertyResponse> properties = military.getProperties().stream().map(militaryPropertyMapper::toMilitaryPropertyResponse).collect(Collectors.toSet());
        militaryResponse.setProperties(properties);
        Set<SpecialtyResponse> specialties = military.getSpecialties().stream().map(specialtyMapper::toSpecialtyResponse).collect(Collectors.toSet());
        militaryResponse.setSpecialties(specialties);
        return militaryResponse;
    }

    public void updateMilitary(Military military, MilitaryPatchRequest militaryPatchRequest) {
        if (militaryPatchRequest.getFirstName() != null) {
            military.setFirstName(militaryPatchRequest.getFirstName());
        }
        if (militaryPatchRequest.getLastName() != null) {
            military.setLastName(militaryPatchRequest.getLastName());
        }
        if (militaryPatchRequest.getMiddleName() != null) {
            military.setMiddleName(militaryPatchRequest.getMiddleName());
        }
        if (militaryPatchRequest.getBirthDate() != null) {
            military.setBirthDate(militaryPatchRequest.getBirthDate());
        }
        if (militaryPatchRequest.getRankId() != null) {
            Rank rank = rankRepository.findById(militaryPatchRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
            military.setRank(rank);
        }
        if (militaryPatchRequest.getUnitId() != null) {
            Unit unit = unitRepository.findById(militaryPatchRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
            military.setUnit(unit);
        }
        if (militaryPatchRequest.getSpecialties() != null) {
            Set<Specialty> specialties = militaryPatchRequest.getSpecialties().stream().map(specialtyMapper::toSpecialty).collect(Collectors.toSet());
            military.setSpecialties(specialties);
        }
    }

}
