package ru.nsu.peretyatko.dto.infrastructure;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlatoonPatchRequest {
    @NotNull(message = "Title cannot be null.")
    private String title;

    private Integer commanderId;

    private Integer companyId;

    private Set<Integer> buildingsId;
}
