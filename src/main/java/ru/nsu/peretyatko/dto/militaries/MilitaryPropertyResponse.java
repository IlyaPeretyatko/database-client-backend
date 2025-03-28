package ru.nsu.peretyatko.dto.militaries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilitaryPropertyResponse {
    private int id;
    private int militaryId;
    private String title;
    private int rankId;
    private String value;
}
