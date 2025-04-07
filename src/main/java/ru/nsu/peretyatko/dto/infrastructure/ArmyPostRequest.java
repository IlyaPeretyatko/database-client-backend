package ru.nsu.peretyatko.dto.infrastructure;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArmyPostRequest {
    @NotNull(message = "Title cannot be null.")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @NotNull(message = "Commander id cannot be null.")
    private Integer commanderId;

    private Set<Integer> corpsId;

    private Set<Integer> divisionsId;

    private Set<Integer> brigadesId;
}
