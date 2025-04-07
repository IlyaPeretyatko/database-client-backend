package ru.nsu.peretyatko.model.weapons;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "weapons_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "category_id"})
})
@Getter
@Setter
public class WeaponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WeaponCategory category;
}
