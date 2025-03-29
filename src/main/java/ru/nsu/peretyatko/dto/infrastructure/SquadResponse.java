package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Integer platoonId;
}
