package ru.nsu.peretyatko.model.buildings;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.peretyatko.model.infrastructure.Unit;

@Entity
@Table(name = "buildings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "unit_id"})
})
@Getter
@Setter
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

}
