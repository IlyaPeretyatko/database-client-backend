package ru.nsu.peretyatko.repository.militaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.militaries.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
}
