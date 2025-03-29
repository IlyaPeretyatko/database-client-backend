package ru.nsu.peretyatko.dto.infrastructure;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPatchRequest {

    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    private Integer commanderId;

    private Integer unitId;
}
