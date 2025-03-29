package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Brigade;

public interface BrigadeRepository extends JpaRepository<Brigade, Integer> {
}
