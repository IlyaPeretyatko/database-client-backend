package ru.nsu.peretyatko.repository.equipments;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.equipments.EquipmentType;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Integer> {
}
