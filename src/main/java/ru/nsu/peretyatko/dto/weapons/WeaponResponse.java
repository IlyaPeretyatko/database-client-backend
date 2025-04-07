package ru.nsu.peretyatko.dto.weapons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeaponResponse {
    private Integer id;

    private String serialNumber;

    private Integer typeId;

    private Integer unitId;
}
