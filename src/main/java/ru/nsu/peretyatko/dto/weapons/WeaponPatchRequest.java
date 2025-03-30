package ru.nsu.peretyatko.dto.weapons;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeaponPatchRequest {
    @Size(min = 2, max = 64, message = "Serial number must be between 2 and 64 characters long.")
    private String serialNumber;

    private Integer typeId;

    private Integer unitId;
}
