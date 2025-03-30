package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.model.militaries.Military;

import java.util.Set;

@Entity
@Table(name = "platoons")
@Getter
@Setter
public class Platoon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commander_id")
    private Military commander;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "buildings_platoons",
            joinColumns = @JoinColumn(name = "platoon_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private Set<Building> buildings;
}