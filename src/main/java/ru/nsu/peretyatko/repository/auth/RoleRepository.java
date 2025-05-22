package ru.nsu.peretyatko.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByTitle(String title);
}
