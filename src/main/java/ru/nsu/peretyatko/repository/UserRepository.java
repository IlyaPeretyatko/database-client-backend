package ru.nsu.peretyatko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.user.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    Optional<User> findByName(String name);
    User findByVerificationCode(String email);
}
