package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Army;

public interface ArmyRepository extends JpaRepository<Army, Integer> {
}
