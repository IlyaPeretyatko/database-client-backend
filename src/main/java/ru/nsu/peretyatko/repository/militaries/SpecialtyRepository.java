package ru.nsu.peretyatko.repository.militaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.militaries.Specialty;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Optional<Specialty> findByTitle(String title);
}
