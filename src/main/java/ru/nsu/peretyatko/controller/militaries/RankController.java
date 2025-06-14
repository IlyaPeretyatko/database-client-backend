package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.militaries.RankRequest;
import ru.nsu.peretyatko.dto.militaries.RankResponse;
import ru.nsu.peretyatko.service.militaries.RankService;
import ru.nsu.peretyatko.validator.militaries.RankValidator;

import java.util.List;

@Tag(name = "Rank API")
@RestController
@RequestMapping("/militaries/ranks")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    private final RankValidator rankValidator;

    @Operation(summary = "Получить перечень званий")
    @GetMapping
    public Page<RankResponse> getRanks(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return rankService.getRanks(page, size);
    }

    @Operation(summary = "Получить звание по ID")
    @GetMapping("/{id}")
    public RankResponse getRankById(@PathVariable int id) {
        return rankService.getRank(id);
    }

    @Operation(summary = "Добавить звание")
    @PostMapping
    public void createRank(@Valid @RequestBody RankRequest rankRequest,
                           BindingResult bindingResult) {
        rankValidator.validate(rankRequest, bindingResult);
        rankService.createRank(rankRequest);
    }

    @Operation(summary = "Удалить звание по ID")
    @DeleteMapping("/{id}")
    public void deleteRank(@PathVariable int id) {
        rankService.deleteRank(id);
    }
}
