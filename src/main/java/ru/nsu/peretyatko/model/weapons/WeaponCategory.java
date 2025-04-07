package ru.nsu.peretyatko.model.weapons;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "weapons_categories")
@Getter
@Setter
public class WeaponCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

}
