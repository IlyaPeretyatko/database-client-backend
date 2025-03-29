package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import ru.nsu.peretyatko.model.militaries.Military;
import java.util.Set;


@Entity
@Table(name = "armies")
@Getter
@Setter
public class Army {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commander_id")
    private Military commander;

    @ManyToMany
    @JoinTable(
            name = "armies_corps",
            joinColumns = @JoinColumn(name = "army_id"),
            inverseJoinColumns = @JoinColumn(name = "corps_id")
    )
    private Set<Corps> corps;

    @ManyToMany
    @JoinTable(
            name = "armies_divisions",
            joinColumns = @JoinColumn(name = "army_id"),
            inverseJoinColumns = @JoinColumn(name = "division_id")
    )
    private Set<Division> divisions;

    @ManyToMany
    @JoinTable(
            name = "armies_brigades",
            joinColumns = @JoinColumn(name = "army_id"),
            inverseJoinColumns = @JoinColumn(name = "brigade_id")
    )
    private Set<Brigade> brigades;
}