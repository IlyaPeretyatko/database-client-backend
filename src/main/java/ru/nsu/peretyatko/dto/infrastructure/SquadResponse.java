package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;
import ru.nsu.peretyatko.dto.buildings.BuildingResponse;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Integer platoonId;

    private Set<BuildingResponse> buildings;
}
