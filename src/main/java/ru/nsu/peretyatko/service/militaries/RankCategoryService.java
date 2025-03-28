package ru.nsu.peretyatko.service.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.militaries.RankCategoryRequest;
import ru.nsu.peretyatko.dto.militaries.RankCategoryResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.militaries.RankCategoryMapper;
import ru.nsu.peretyatko.model.militaries.RankCategory;
import ru.nsu.peretyatko.repository.militaries.RankCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankCategoryService {

    private final RankCategoryRepository rankCategoryRepository;

    private final RankCategoryMapper rankCategoryMapper;

    @Transactional(readOnly = true)
    public List<RankCategoryResponse> getRankCategories() {
        return rankCategoryRepository.findAll().stream().map(rankCategoryMapper::toRankCategoryResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RankCategoryResponse getRankCategory(int id) {
        RankCategory rankCategory = rankCategoryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Rank category was not found."));
        return rankCategoryMapper.toRankCategoryResponse(rankCategory);
    }

    @Transactional
    public void createRankCategory(RankCategoryRequest rankCategoryRequest) {
        rankCategoryRepository.save(rankCategoryMapper.toRankCategory(rankCategoryRequest));
    }

    @Transactional
    public void deleteRankCategory(int id) {
        if (!rankCategoryRepository.existsById(id)) { throw new ServiceException(404, "Rank category was not found."); }
        rankCategoryRepository.deleteById(id);
    }
}
