package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;
import ru.nsu.peretyatko.dto.buildings.BuildingResponse;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlatoonResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Integer companyId;

    private Set<BuildingResponse> buildings;
}
