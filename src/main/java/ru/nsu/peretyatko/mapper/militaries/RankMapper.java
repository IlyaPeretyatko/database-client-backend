package ru.nsu.peretyatko.mapper.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.RankRequest;
import ru.nsu.peretyatko.dto.militaries.RankResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.militaries.Rank;
import ru.nsu.peretyatko.model.militaries.RankCategory;
import ru.nsu.peretyatko.repository.militaries.RankCategoryRepository;

@Component
@RequiredArgsConstructor
public class RankMapper {

    private final RankCategoryRepository rankCategoryRepository;

    public Rank toRank(RankRequest rankRequest) {
        Rank rank = new Rank();
        rank.setTitle(rankRequest.getTitle());
        rank.setLevel(rankRequest.getLevel());
        RankCategory rankCategory = rankCategoryRepository.findById(rankRequest.getRankCategoryId()).orElseThrow(() -> new ServiceException(404, "Rank category was not found."));
        rank.setRankCategory(rankCategory);
        return rank;
    }

    public RankResponse toRankResponse(Rank rank) {
        RankResponse rankResponse = new RankResponse();
        rankResponse.setId(rank.getId());
        rankResponse.setTitle(rank.getTitle());
        rankResponse.setLevel(rank.getLevel());
        rankResponse.setRankCategoryId(rank.getRankCategory().getId());
        return rankResponse;
    }


}
