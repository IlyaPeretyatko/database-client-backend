package ru.nsu.peretyatko.dto.equipments;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentPropertyResponse {
    private Integer id;

    private String title;

    private String value;

    private Integer typeId;
}
