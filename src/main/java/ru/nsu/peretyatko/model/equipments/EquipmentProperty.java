package ru.nsu.peretyatko.model.equipments;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "military_equipment_properties", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "type_id"})
})
@Getter
@Setter
public class EquipmentProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private EquipmentType type;
}
