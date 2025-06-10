package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;

@Entity
@Table(name = "brigades_units")
public class BrigadeUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "brigade_id")
    private Brigade brigade;
}
