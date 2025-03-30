package ru.nsu.peretyatko.dto.weapons;

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
public class WeaponPostRequest {
    @NotNull(message = "Serial number cannot be null.")
    @Size(min = 2, max = 64, message = "Serial number must be between 2 and 64 characters long.")
    private String serialNumber;

    @NotNull(message = "Type id cannot be null.")
    private Integer typeId;

    @NotNull(message = "Unit id cannot be null.")
    private Integer unitId;
}
