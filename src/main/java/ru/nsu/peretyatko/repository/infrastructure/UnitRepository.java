package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
