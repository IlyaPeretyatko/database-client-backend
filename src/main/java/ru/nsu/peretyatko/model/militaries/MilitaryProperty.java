package ru.nsu.peretyatko.model.militaries;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "militaries_properties", uniqueConstraints = @UniqueConstraint(columnNames = {"military_id", "title", "rank_id"}))
public class MilitaryProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "military_id", nullable = false)
    private Military military;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    private String value;
}