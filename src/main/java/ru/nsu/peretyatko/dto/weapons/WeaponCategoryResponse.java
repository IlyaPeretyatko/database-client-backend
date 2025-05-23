package ru.nsu.peretyatko.dto.weapons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeaponCategoryResponse {
    private Integer id;

    private String title;
}
