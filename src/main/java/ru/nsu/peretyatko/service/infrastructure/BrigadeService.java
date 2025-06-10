package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePostRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadeResponse;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.BrigadeMapper;
import ru.nsu.peretyatko.model.infrastructure.Brigade;
import ru.nsu.peretyatko.repository.infrastructure.BrigadeCustomRepository;
import ru.nsu.peretyatko.repository.infrastructure.BrigadeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrigadeService {

    private final BrigadeCustomRepository brigadeCustomRepository;

    private final BrigadeRepository brigadeRepository;

    private final BrigadeMapper brigadeMapper;

    @Transactional(readOnly = true)
    public Page<BrigadeResponse> getBrigades(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return brigadeRepository.findAll(pageable).map(brigadeMapper::toBrigadeResponse);
    }

    @Transactional(readOnly = true)
    public BrigadeResponse getBrigade(int id) {
        Brigade brigade = brigadeRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Brigade was not found."));
        return brigadeMapper.toBrigadeResponse(brigade);
    }

    @Transactional
    public void createBrigade(BrigadePostRequest brigadePostRequest) {
        brigadeRepository.save(brigadeMapper.toBrigade(brigadePostRequest));
    }

    @Transactional
    public void updateBrigade(int id, BrigadePatchRequest brigadePatchRequest) {
        Brigade brigade = brigadeRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Brigade was not found."));
        brigadeMapper.updateBrigade(brigade, brigadePatchRequest);
        brigadeRepository.save(brigade);
    }

    @Transactional
    public void deleteBrigade(int id) {
        if (!brigadeRepository.existsById(id)) {
            throw new ServiceException(404, "Brigade was not found.");
        }
        brigadeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BrigadeResponse getBrigadeWithMostUnits() {
        return brigadeMapper.toBrigadeResponse(brigadeCustomRepository.findBrigadeWithMostUnits());
    }

    @Transactional(readOnly = true)
    public BrigadeResponse getBrigadeWithFewestUnits() {
        return brigadeMapper.toBrigadeResponse(brigadeCustomRepository.findBrigadeWithFewestUnits());
    }

}