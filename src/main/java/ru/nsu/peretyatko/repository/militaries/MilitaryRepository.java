package ru.nsu.peretyatko.repository.militaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.militaries.Military;

public interface MilitaryRepository extends JpaRepository<Military, Integer> {
}
