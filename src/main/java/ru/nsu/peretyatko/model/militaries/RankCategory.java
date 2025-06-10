package ru.nsu.peretyatko.model.militaries;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rank_categories")
public class RankCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    public RankCategory(String title) {
        this.title = title;
    }

}