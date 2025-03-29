package ru.nsu.peretyatko.dto.infrastructure;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlatoonResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Integer companyId;
}
