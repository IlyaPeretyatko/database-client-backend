package ru.nsu.peretyatko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
