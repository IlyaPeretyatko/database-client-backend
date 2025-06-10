package ru.nsu.peretyatko.model.militaries;

import jakarta.persistence.*;
import lombok.*;
import ru.nsu.peretyatko.model.infrastructure.CorpsUnit;

import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "specialties")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MilitarySpecialty> militarySpecialties;
}