package ru.nsu.peretyatko.dto.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DivisionResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Set<UnitResponse> units;
}
