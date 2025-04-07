package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.SquadPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.SquadResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.SquadMapper;
import ru.nsu.peretyatko.model.infrastructure.Squad;
import ru.nsu.peretyatko.repository.infrastructure.SquadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SquadService {
    private final SquadRepository squadRepository;

    private final SquadMapper squadMapper;

    @Transactional(readOnly = true)
    public List<SquadResponse> getSquads() {
        return squadRepository.findAll().stream().map(squadMapper::toSquadResponse).toList();
    }

    @Transactional(readOnly = true)
    public SquadResponse getSquad(int id) {
        Squad squad = squadRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Squad was not found."));
        return squadMapper.toSquadResponse(squad);
    }

    @Transactional
    public void createSquad(SquadPostRequest squadPostRequest) {
        squadRepository.save(squadMapper.toSquad(squadPostRequest));
    }

    @Transactional
    public void updateSquad(int id, SquadPatchRequest squadPatchRequest) {
        Squad squad = squadRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Squad was not found."));
        squadMapper.updateSquad(squad, squadPatchRequest);
        squadRepository.save(squad);
    }

    @Transactional
    public void deleteSquad(int id) {
        if (!squadRepository.existsById(id)) {
            throw new ServiceException(404, "Squad was not found.");
        }
        squadRepository.deleteById(id);
    }
}