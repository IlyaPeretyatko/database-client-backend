package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.RankCategoryRequest;
import ru.nsu.peretyatko.dto.militaries.RankCategoryResponse;
import ru.nsu.peretyatko.service.militaries.RankCategoryService;
import ru.nsu.peretyatko.validator.militaries.RankCategoryValidator;

import java.util.List;

@Tag(name = "Rank API")
@RestController
@RequestMapping("/militaries/ranks/categories")
@RequiredArgsConstructor
public class RankCategoryController {

    private final RankCategoryService rankCategoryService;

    private final RankCategoryValidator rankCategoryValidator;

    @Operation(summary = "Получить перечень категорий званий")
    @GetMapping
    public Page<RankCategoryResponse> getRankCategories(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return rankCategoryService.getRankCategories(page, size);
    }

    @Operation(summary = "Получить категорию званий по ID")
    @GetMapping("/{id}")
    public RankCategoryResponse getRankCategoryById(@PathVariable int id) {
        return rankCategoryService.getRankCategory(id);
    }

    @Operation(summary = "Добавить категорию званий")
    @PostMapping
    public void createRankCategory(@Valid @RequestBody RankCategoryRequest rankCategoryRequest,
                                   BindingResult bindingResult) {
        rankCategoryValidator.validate(rankCategoryRequest, bindingResult);
        rankCategoryService.createRankCategory(rankCategoryRequest);
    }

    @Operation(summary = "Удалить категорию званий по ID")
    @DeleteMapping("/{id}")
    public void deleteRankCategory(@PathVariable int id) {
        rankCategoryService.deleteRankCategory(id);
    }
}
