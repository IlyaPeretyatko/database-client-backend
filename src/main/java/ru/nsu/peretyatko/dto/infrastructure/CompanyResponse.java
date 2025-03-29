package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Integer unitId;
}
