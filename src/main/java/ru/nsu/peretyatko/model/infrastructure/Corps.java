package ru.nsu.peretyatko.model.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import ru.nsu.peretyatko.model.militaries.Military;

import java.util.Set;

@Entity
@Table(name = "corps")
@Getter
@Setter
public class Corps {
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
            name = "corps_units",
            joinColumns = @JoinColumn(name = "corps_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    private Set<Unit> units;


    @OneToMany(mappedBy = "corps", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArmyCorps> armyCorps;
}
