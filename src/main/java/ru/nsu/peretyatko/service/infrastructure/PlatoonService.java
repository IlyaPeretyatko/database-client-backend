package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.PlatoonMapper;
import ru.nsu.peretyatko.model.infrastructure.Platoon;
import ru.nsu.peretyatko.repository.infrastructure.PlatoonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatoonService {
    private final PlatoonRepository platoonRepository;

    private final PlatoonMapper platoonMapper;

    @Transactional(readOnly = true)
    public List<PlatoonResponse> getPlatoons() {
        return platoonRepository.findAll().stream().map(platoonMapper::toPlatoonResponse).toList();
    }

    @Transactional(readOnly = true)
    public PlatoonResponse getPlatoon(int id) {
        Platoon platoon = platoonRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Platoon was not found."));
        return platoonMapper.toPlatoonResponse(platoon);
    }

    @Transactional
    public void createPlatoon(PlatoonPostRequest platoonPostRequest) {
        platoonRepository.save(platoonMapper.toPlatoon(platoonPostRequest));
    }

    @Transactional
    public void updatePlatoon(int id, PlatoonPatchRequest platoonPatchRequest) {
        Platoon platoon = platoonRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Platoon was not found."));
        platoonMapper.updatePlatoon(platoon, platoonPatchRequest);
        platoonRepository.save(platoon);
    }

    @Transactional
    public void deletePlatoon(int id) {
        if (!platoonRepository.existsById(id)) {
            throw new ServiceException(404, "Platoon was not found.");
        }
        platoonRepository.deleteById(id);
    }
}
