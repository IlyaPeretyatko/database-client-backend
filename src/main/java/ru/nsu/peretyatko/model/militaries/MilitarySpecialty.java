package ru.nsu.peretyatko.model.militaries;

import jakarta.persistence.*;

@Entity
@Table(name = "militaries_specialties")
public class MilitarySpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "military_id")
    private Military military;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
}
