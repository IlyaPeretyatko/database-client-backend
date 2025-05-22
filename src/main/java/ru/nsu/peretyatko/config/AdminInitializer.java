package ru.nsu.peretyatko.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.model.auth.Role;
import ru.nsu.peretyatko.model.auth.User;
import ru.nsu.peretyatko.repository.auth.RoleRepository;
import ru.nsu.peretyatko.repository.auth.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByName("admin")) {
            User user = new User();
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("Qwerty123!"));
            user.setEmail("i.peretyatko@g.nsu.ru");
            user.setEmailConfirmed(true);
            user.setVerificationCode(UUID.randomUUID().toString());
            Optional<Role> adminRole = roleRepository.findByTitle("ROLE_ADMIN");
            Optional<Role> userRole = roleRepository.findByTitle("ROLE_USER");
            if (adminRole.isPresent() && userRole.isPresent()) {
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole.get());
                roles.add(userRole.get());
                user.setRoles(roles);
            }
            userRepository.save(user);
        }
    }
}
