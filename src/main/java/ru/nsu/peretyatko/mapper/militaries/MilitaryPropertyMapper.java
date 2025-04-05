package ru.nsu.peretyatko.mapper.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPostRequest;
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

    public MilitaryProperty toMilitaryProperty(MilitaryPropertyPostRequest militaryPropertyPostRequest) {
        MilitaryProperty militaryProperty = new MilitaryProperty();
        Military military = militaryRepository.findById(militaryPropertyPostRequest.getMilitaryId()).orElseThrow(() -> new ServiceException(404, "Military was not found."));
        militaryProperty.setMilitary(military);
        militaryProperty.setTitle(militaryPropertyPostRequest.getTitle());
        Rank rank = rankRepository.findById(militaryPropertyPostRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
        militaryProperty.setRank(rank);
        militaryProperty.setValue(militaryPropertyPostRequest.getValue());
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

    public void updateMilitaryProperty(MilitaryProperty militaryProperty, MilitaryPropertyPatchRequest militaryPropertyPatchRequest) {
        if (militaryPropertyPatchRequest.getMilitaryId() != null) {
            Military military = militaryRepository.findById(militaryPropertyPatchRequest.getMilitaryId()).orElseThrow(() -> new ServiceException(404, "Military was not found."));
            militaryProperty.setMilitary(military);
        }
        if (militaryPropertyPatchRequest.getTitle() != null) {
            militaryProperty.setTitle(militaryPropertyPatchRequest.getTitle());
        }
        if (militaryPropertyPatchRequest.getRankId() != null) {
            Rank rank = rankRepository.findById(militaryPropertyPatchRequest.getRankId()).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
            militaryProperty.setRank(rank);
        }
        if (militaryPropertyPatchRequest.getValue() != null) {
            militaryProperty.setValue(militaryPropertyPatchRequest.getValue());
        }
    }

}
