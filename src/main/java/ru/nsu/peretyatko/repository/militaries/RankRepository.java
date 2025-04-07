package ru.nsu.peretyatko.repository.militaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.militaries.Rank;

public interface RankRepository extends JpaRepository<Rank, Integer> {
}
