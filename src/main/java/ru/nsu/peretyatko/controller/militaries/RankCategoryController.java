package ru.nsu.peretyatko.controller.militaries;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.RankCategoryRequest;
import ru.nsu.peretyatko.dto.militaries.RankCategoryResponse;
import ru.nsu.peretyatko.service.militaries.RankCategoryService;
import ru.nsu.peretyatko.validator.militaries.RankCategoryValidator;

import java.util.List;

@Controller
@RequestMapping("/api/military/rank/category")
@RequiredArgsConstructor
public class RankCategoryController {

    private final RankCategoryService rankCategoryService;

    private final RankCategoryValidator rankCategoryValidator;

    @GetMapping
    public List<RankCategoryResponse> getRankCategories() {
        return rankCategoryService.getRankCategories();
    }

    @GetMapping("/{id}")
    public RankCategoryResponse getRankCategory(@PathVariable int id) {
        return rankCategoryService.getRankCategory(id);
    }

    @PostMapping
    public void createRankCategory(@Valid @RequestBody RankCategoryRequest rankCategoryRequest,
                                   BindingResult bindingResult) {
        rankCategoryValidator.validate(rankCategoryRequest, bindingResult);
        rankCategoryService.createRankCategory(rankCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRankCategory(@PathVariable int id) {
        rankCategoryService.deleteRankCategory(id);
    }
}
