package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;

@Entity
@Table(name = "armies_brigades")
public class ArmyBrigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "army_id")
    private Army army;

    @ManyToOne
    @JoinColumn(name = "brigade_id")
    private Brigade brigade;
}
