package ru.nsu.peretyatko.model.equipments;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "military_equipment_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "category_id"})
})
@Getter
@Setter
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private EquipmentCategory category;

}