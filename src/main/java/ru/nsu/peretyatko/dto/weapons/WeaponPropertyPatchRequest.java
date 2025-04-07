package ru.nsu.peretyatko.dto.weapons;

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
public class WeaponPropertyPatchRequest {
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @NotEmpty(message = "Value cannot be empty.")
    private String value;

    private Integer typeId;
}
