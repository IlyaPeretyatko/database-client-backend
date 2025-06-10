package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;

@Entity
@Table(name = "armies_corps")
public class ArmyCorps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "army_id")
    private Army army;

    @ManyToOne
    @JoinColumn(name = "corps_id")
    private Corps corps;
}
