package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Corps;

public interface CorpsRepository extends JpaRepository<Corps, Integer> {
}
