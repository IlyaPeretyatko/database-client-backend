package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.SquadPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Platoon;
import ru.nsu.peretyatko.model.infrastructure.Squad;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.infrastructure.PlatoonRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

@Component
@RequiredArgsConstructor
public class SquadMapper {
    private final MilitaryRepository militaryRepository;

    private final PlatoonRepository platoonRepository;

    public Squad toSquad(SquadPostRequest squadPostRequest) {
        Squad squad = new Squad();
        squad.setTitle(squadPostRequest.getTitle());
        Military military = militaryRepository.findById(squadPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        squad.setCommander(military);
        Platoon platoon = platoonRepository.findById(squadPostRequest.getPlatoonId()).orElseThrow(() -> new ServiceException(404, "Platoon eas not found."));
        squad.setPlatoon(platoon);
        return squad;
    }

    public SquadResponse toSquadResponse(Squad squad) {
        SquadResponse squadResponse = new SquadResponse();
        squadResponse.setId(squad.getId());
        squadResponse.setTitle(squad.getTitle());
        squadResponse.setCommanderId(squad.getCommander().getId());
        squadResponse.setPlatoonId(squad.getPlatoon().getId());
        return squadResponse;
    }

    public void updateSquad(Squad squad, SquadPatchRequest squadPatchRequest) {
        if (squadPatchRequest.getTitle() != null) {
            squad.setTitle(squadPatchRequest.getTitle());
        }
        if (squadPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(squadPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            squad.setCommander(military);
        }
        if (squadPatchRequest.getPlatoonId() != null) {
            Platoon platoon = platoonRepository.findById(squadPatchRequest.getPlatoonId()).orElseThrow(() -> new ServiceException(404, "Platoon eas not found."));
            squad.setPlatoon(platoon);
        }
    }
}
