package ru.nsu.peretyatko.mapper.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.MilitaryProperty;
import ru.nsu.peretyatko.model.militaries.Rank;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;
import ru.nsu.peretyatko.repository.militaries.RankRepository;

@Component
@RequiredArgsConstructor
public class MilitaryPropertyMapper {

    private final MilitaryRepository militaryRepository;

    private final RankRepository rankRepository;

    public MilitaryProperty toMilitaryProperty(MilitaryPropertyRequest militaryPropertyRequest) {
        MilitaryProperty militaryProperty = new MilitaryProperty();
        Military military = militaryRepository.findById(militaryPropertyRequest.getMilitaryId()).orElseThrow(() -> new ServiceException(404, "Military was not found."));
        militaryProperty.setMilitary(military);
        militaryProperty.setTitle(militaryPropertyRequest.getTitle());
        Rank rank = rankRepository.findById(militaryPropertyRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
        militaryProperty.setRank(rank);
        militaryProperty.setValue(militaryPropertyRequest.getValue());
        return militaryProperty;
    }

    public MilitaryPropertyResponse toMilitaryPropertyResponse(MilitaryProperty militaryProperty) {
        MilitaryPropertyResponse militaryPropertyResponse = new MilitaryPropertyResponse();
        militaryPropertyResponse.setId(militaryProperty.getId());
        militaryPropertyResponse.setTitle(militaryProperty.getTitle());
        militaryPropertyResponse.setValue(militaryProperty.getValue());
        militaryPropertyResponse.setMilitaryId(militaryProperty.getMilitary().getId());
        militaryPropertyResponse.setRankId(militaryProperty.getRank().getId());
        return militaryPropertyResponse;
    }

}
