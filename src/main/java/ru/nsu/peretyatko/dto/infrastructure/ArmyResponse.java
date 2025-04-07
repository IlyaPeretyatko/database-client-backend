package ru.nsu.peretyatko.dto.infrastructure;

import java.util.Set;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArmyResponse {
    private Integer id;

    private String title;

    private Integer commanderId;

    private Set<CorpsResponse> corps;

    private Set<DivisionResponse> divisions;

    private Set<BrigadeResponse> brigades;
}
