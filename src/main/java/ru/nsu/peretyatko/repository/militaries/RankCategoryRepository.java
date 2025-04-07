package ru.nsu.peretyatko.repository.militaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.militaries.RankCategory;

public interface RankCategoryRepository extends JpaRepository<RankCategory, Integer> {
}
