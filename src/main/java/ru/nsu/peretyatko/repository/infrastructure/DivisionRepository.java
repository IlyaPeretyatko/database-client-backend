package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Division;

public interface DivisionRepository extends JpaRepository<Division, Integer> {
}
