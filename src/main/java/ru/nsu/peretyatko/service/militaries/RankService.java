package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.RankRequest;
import ru.nsu.peretyatko.dto.militaries.RankResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.RankMapper;
import ru.nsu.peretyatko.model.militaries.Rank;
import ru.nsu.peretyatko.repository.militaries.RankRepository;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class RankService {

    private final RankRepository rankRepository;

    private final RankMapper rankMapper;

    @Transactional(readOnly = true)
    public Page<RankResponse> getRanks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rankRepository.findAll(pageable).map(rankMapper::toRankResponse);
    }

    @Transactional(readOnly = true)
    public RankResponse getRank(int id) {
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Rank was not found."));
        return rankMapper.toRankResponse(rank);
    }

    @Transactional
    public void createRank(RankRequest rankRequest) {
        rankRepository.save(rankMapper.toRank(rankRequest));
    }

    @Transactional
    public void deleteRank(int id) {
        if (!rankRepository.existsById(id)) { throw new ServiceException(404, "Rank was not found."); }
        rankRepository.deleteById(id);
    }

}
