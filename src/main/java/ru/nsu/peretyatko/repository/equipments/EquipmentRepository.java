package ru.nsu.peretyatko.repository.equipments;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.equipments.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
