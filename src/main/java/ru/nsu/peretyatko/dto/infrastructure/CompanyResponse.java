package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;
import ru.nsu.peretyatko.dto.buildings.BuildingResponse;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Integer unitId;

    private Set<BuildingResponse> buildings;
}
