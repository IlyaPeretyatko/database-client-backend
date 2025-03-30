package ru.nsu.peretyatko.repository.buildngs;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.buildings.BuildingProperty;

public interface BuildingPropertyRepository extends JpaRepository<BuildingProperty, Integer> {
}
