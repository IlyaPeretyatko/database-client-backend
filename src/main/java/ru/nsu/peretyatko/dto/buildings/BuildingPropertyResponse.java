package ru.nsu.peretyatko.dto.buildings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingPropertyResponse {
    private Integer id;

    private String title;

    private String value;

    private Integer buildingId;
}
