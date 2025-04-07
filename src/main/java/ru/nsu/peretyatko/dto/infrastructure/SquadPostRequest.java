package ru.nsu.peretyatko.dto.infrastructure;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadPostRequest {
    @NotNull(message = "Title cannot be null.")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @NotNull(message = "Commander id cannot be null.")
    private Integer commanderId;

    @NotNull(message = "Platoon id cannot be null.")
    private Integer platoonId;

    private Set<Integer> buildingsId;
}
