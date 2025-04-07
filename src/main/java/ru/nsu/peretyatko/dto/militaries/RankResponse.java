package ru.nsu.peretyatko.dto.militaries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankResponse {
    private int id;
    private String title;
    private int level;
    private int rankCategoryId;
}
