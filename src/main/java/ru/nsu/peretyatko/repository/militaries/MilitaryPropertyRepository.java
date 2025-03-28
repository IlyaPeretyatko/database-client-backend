package ru.nsu.peretyatko.repository.militaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.militaries.MilitaryProperty;

public interface MilitaryPropertyRepository extends JpaRepository<MilitaryProperty, Integer> {
}
