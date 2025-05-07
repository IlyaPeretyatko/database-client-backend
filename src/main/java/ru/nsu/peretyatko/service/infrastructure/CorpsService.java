package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsResponse;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.CorpsMapper;
import ru.nsu.peretyatko.model.infrastructure.Corps;
import ru.nsu.peretyatko.repository.infrastructure.CorpsCustomRepository;
import ru.nsu.peretyatko.repository.infrastructure.CorpsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorpsService {

    private final CorpsCustomRepository corpsCustomRepository;

    private final CorpsRepository corpsRepository;

    private final CorpsMapper corpsMapper;

    @Transactional(readOnly = true)
    public List<CorpsResponse> getCorps() {
        return corpsRepository.findAll().stream().map(corpsMapper::toCorpsResponse).toList();
    }

    @Transactional(readOnly = true)
    public CorpsResponse getCorps(int id) {
        Corps corps = corpsRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Corps was not found."));
        return corpsMapper.toCorpsResponse(corps);
    }

    @Transactional
    public void createCorps(CorpsPostRequest corpsPostRequest) {
        corpsRepository.save(corpsMapper.toCorps(corpsPostRequest));
    }

    @Transactional
    public void updateCorps(int id, CorpsPatchRequest corpsPatchRequest) {
        Corps corps = corpsRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Corps was not found."));
        corpsMapper.updateCorps(corps, corpsPatchRequest);
        corpsRepository.save(corps);
    }

    @Transactional
    public void deleteCorps(int id) {
        if (!corpsRepository.existsById(id)) {
            throw new ServiceException(404, "Corps was not found.");
        }
        corpsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CorpsResponse getCorpsWithMostUnits() {
        return corpsMapper.toCorpsResponse(corpsCustomRepository.findCorpsWithMostUnits());
    }

    @Transactional(readOnly = true)
    public CorpsResponse getCorpsWithFewestUnits() {
        return corpsMapper.toCorpsResponse(corpsCustomRepository.findCorpsWithFewestUnits());
    }
}