package ru.nsu.peretyatko.controller.militaries;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<RankResponse> getRanks() {
        return rankService.getRanks();
    }

    @GetMapping("/{id}")
    public RankResponse getRankById(@PathVariable int id) {
        return rankService.getRank(id);
    }

    @PostMapping
    public void createRank(@Valid @RequestBody RankRequest rankRequest,
                           BindingResult bindingResult) {
        rankValidator.validate(rankRequest, bindingResult);
        rankService.createRank(rankRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRank(@PathVariable int id) {
        rankService.deleteRank(id);
    }
}
