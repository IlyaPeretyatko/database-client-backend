package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CorpsCustomRepository {

    private EntityManager entityManager;

}
