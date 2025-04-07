package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrigadeResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Set<UnitResponse> units;
}
