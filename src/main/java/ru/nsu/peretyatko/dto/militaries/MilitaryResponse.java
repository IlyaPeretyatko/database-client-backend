package ru.nsu.peretyatko.dto.militaries;

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
public class MilitaryResponse {

    private int id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date birthDate;

    private int rankId;

    private Integer unitId;

    private Set<MilitaryPropertyResponse> properties;

    private Set<SpecialtyResponse> specialties;
}
