package ru.nsu.peretyatko.mapper.militaries;

import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.militaries.RankCategoryRequest;
import ru.nsu.peretyatko.dto.militaries.RankCategoryResponse;
import ru.nsu.peretyatko.model.militaries.RankCategory;

@Component
public class RankCategoryMapper {

    public RankCategory toRankCategory(RankCategoryRequest rankCategoryRequest) {
        RankCategory rankCategory = new RankCategory();
        rankCategory.setTitle(rankCategoryRequest.getTitle());
        return rankCategory;
    }

    public RankCategoryResponse toRankCategoryResponse(RankCategory rankCategory) {
        RankCategoryResponse rankCategoryResponse = new RankCategoryResponse();
        rankCategoryResponse.setId(rankCategory.getId());
        rankCategoryResponse.setTitle(rankCategory.getTitle());
        return rankCategoryResponse;
    }

}
