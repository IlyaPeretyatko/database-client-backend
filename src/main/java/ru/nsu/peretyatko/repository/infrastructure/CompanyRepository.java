package ru.nsu.peretyatko.repository.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.infrastructure.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
