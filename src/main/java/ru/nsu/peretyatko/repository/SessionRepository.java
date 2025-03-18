package ru.nsu.peretyatko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.Session;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
