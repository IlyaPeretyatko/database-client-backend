package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;

@Entity
@Table(name = "armies_divisions")
public class ArmyDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "army_id")
    private Army army;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
}
