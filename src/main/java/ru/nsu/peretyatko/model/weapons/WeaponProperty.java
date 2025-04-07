package ru.nsu.peretyatko.model.weapons;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "weapons_properties", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "type_id"})
})
@Getter
@Setter
public class WeaponProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private WeaponType type;
}