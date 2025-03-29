package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Squad;

public interface SquadRepository extends JpaRepository<Squad, Integer> {
}
