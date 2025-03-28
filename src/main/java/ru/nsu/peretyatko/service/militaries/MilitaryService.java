package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.MilitaryRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.MilitaryMapper;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilitaryService {
    private final MilitaryRepository militaryRepository;

    private final MilitaryMapper militaryMapper;

    @Transactional(readOnly = true)
    public List<MilitaryResponse> getMilitaries() {
        return militaryRepository.findAll().stream().map(militaryMapper::toMilitaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public MilitaryResponse getMilitary(int id) {
        Military military = militaryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military was not found."));
        return militaryMapper.toMilitaryResponse(military);
    }

    @Transactional
    public void createMilitary(MilitaryRequest militaryRequest) {
        militaryRepository.save(militaryMapper.toMilitary(militaryRequest));
    }

    @Transactional
    public void updateMilitary(int id, MilitaryRequest militaryRequest) {
        Military military = militaryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Military was not found."));
        militaryMapper.updateMilitary(military, militaryRequest);
        militaryRepository.save(military);
    }

    @Transactional
    public void deleteMilitary(int id) {
        if (!militaryRepository.existsById(id)) { throw new ServiceException(404, "Military was not found."); }
        militaryRepository.deleteById(id);
    }


}
