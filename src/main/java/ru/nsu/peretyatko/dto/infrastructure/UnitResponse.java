package ru.nsu.peretyatko.dto.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.peretyatko.dto.equipments.EquipmentResponse;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitResponse {
    private Integer id;

    private String title;

    private Double latitude;

    private Double longitude;

    private Integer commanderId;

    private Set<WeaponResponse> weapons;

    private Set<EquipmentResponse> equipments;

}
