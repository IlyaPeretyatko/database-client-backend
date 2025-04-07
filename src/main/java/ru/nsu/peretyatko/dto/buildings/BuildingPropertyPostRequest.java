package ru.nsu.peretyatko.dto.buildings;

import jakarta.validation.constraints.NotEmpty;
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
public class BuildingPropertyPostRequest {
    @NotNull(message = "Title cannot be null.")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @NotNull(message = "Value cannot be null.")
    @NotEmpty(message = "Value cannot be empty.")
    private String value;

    @NotNull(message = "Building id cannot be null.")
    private Integer buildingId;
}
