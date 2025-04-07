package ru.nsu.peretyatko.repository.equipments;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.equipments.EquipmentProperty;

public interface EquipmentPropertyRepository extends JpaRepository<EquipmentProperty, Integer> {
}
