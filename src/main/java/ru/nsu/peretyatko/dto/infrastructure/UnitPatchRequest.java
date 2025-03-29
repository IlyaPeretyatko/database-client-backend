package ru.nsu.peretyatko.dto.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitPatchRequest {
    private String title;

    private Double latitude;

    private Double longitude;

    private Integer commanderId;

}
