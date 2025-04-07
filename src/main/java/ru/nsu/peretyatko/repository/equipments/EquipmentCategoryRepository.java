package ru.nsu.peretyatko.repository.equipments;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.equipments.EquipmentCategory;

public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategory, Integer> {
}
