package ru.nsu.peretyatko.dto.infrastructure;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitPatchRequest {
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    private Double latitude;

    private Double longitude;

    private Integer commanderId;

    private Set<Integer> weaponsId;

    private Set<Integer> equipmentsId;
}
