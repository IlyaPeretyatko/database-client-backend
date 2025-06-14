package ru.nsu.peretyatko.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.nsu.peretyatko.model.auth.Role;
import ru.nsu.peretyatko.model.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity createJwtEntity(User user) {
        return new JwtEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Role::getTitle)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
