package ru.nsu.peretyatko.dto.infrastructure;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class UnitPostRequest {
    @NotNull(message = "Title cannot be null.")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters long.")
    private String title;

    @NotNull(message = "Latitude cannot be null.")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null.")
    private Double longitude;

    @NotNull(message = "Commander id cannot be null.")
    private Integer commanderId;

    private Set<Integer> weapons;

    private Set<Integer> equipments;
}
