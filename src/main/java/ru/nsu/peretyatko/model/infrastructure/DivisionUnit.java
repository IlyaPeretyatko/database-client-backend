package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;

@Entity
@Table(name = "divisions_units")
public class DivisionUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
}
