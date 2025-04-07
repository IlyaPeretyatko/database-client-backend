package ru.nsu.peretyatko.dto.buildings;

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
public class BuildingPostRequest {
    @NotNull(message = "Title cannot be null.")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @Size(min = 2, max = 64, message = "Address must be between 2 and 64 characters long.")
    private String address;

    @NotNull(message = "Unit id cannot be null.")
    private Integer unitId;
}
