package ru.nsu.peretyatko.dto.militaries;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilitaryPatchRequest {

    @Size(min = 2, max = 64, message = "First name must be between 2 and 64 characters long.")
    private String firstName;


    @Size(min = 2, max = 64, message = "Last name must be between 2 and 64 characters long.")
    private String lastName;

    @Size(min = 2, max = 64, message = "Middle name must be between 2 and 64 characters long.")
    private String middleName;

    private Date birthDate;

    private Integer rankId;

    private Set<SpecialtyRequest> specialties;
}
