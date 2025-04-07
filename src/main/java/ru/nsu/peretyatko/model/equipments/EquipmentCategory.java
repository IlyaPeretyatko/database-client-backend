package ru.nsu.peretyatko.model.equipments;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "military_equipment_categories")
@Getter
@Setter
public class EquipmentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

}