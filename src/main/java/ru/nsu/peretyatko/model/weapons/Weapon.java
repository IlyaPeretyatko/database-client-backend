package ru.nsu.peretyatko.model.weapons;

import lombok.*;
import jakarta.persistence.*;
import ru.nsu.peretyatko.model.infrastructure.Unit;

@Entity
@Table(name = "weapons")
@Getter
@Setter
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private WeaponType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
