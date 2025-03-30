package ru.nsu.peretyatko.repository.buildngs;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.buildings.Building;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
