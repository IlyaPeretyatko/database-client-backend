package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Platoon;

public interface PlatoonRepository extends JpaRepository<Platoon, Integer> {
}
