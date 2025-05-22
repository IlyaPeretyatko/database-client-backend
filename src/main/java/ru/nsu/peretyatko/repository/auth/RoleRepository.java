package ru.nsu.peretyatko.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.auth.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByTitle(String title);
    Optional<Role> findByTitle(String title);
}
