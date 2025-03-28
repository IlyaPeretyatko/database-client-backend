package ru.nsu.peretyatko.dto.militaries;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilitaryPropertyRequest {

    @NotNull(message = "Military id cannot be null.")
    private Integer militaryId;

    @NotNull(message = "Title cannot be null.")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @NotNull(message = "Rank id cannot be null.")
    private Integer rankId;

    @NotNull(message = "Value cannot be null.")
    @Size(min = 2, max = 64, message = "Value must be between 2 and 64 characters long.")
    private String value;
}
