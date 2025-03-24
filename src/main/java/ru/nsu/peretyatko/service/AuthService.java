package ru.nsu.peretyatko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.peretyatko.dto.auth.JwtRequest;
import ru.nsu.peretyatko.dto.auth.JwtResponse;
import ru.nsu.peretyatko.dto.auth.RefreshRequest;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.error.exception.ValidationException;
import ru.nsu.peretyatko.model.user.User;
import ru.nsu.peretyatko.repository.UserRepository;
import ru.nsu.peretyatko.security.JwtTokenProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponse login(JwtRequest jwtRequest) {
        User user = userService.getUserByName(jwtRequest.getUsername());
        if (!passwordEncoder.matches(jwtRequest.getPassword(), user.getPassword())) {
            throw new ServiceException(401, "Password is wrong.");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getName());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getName(), user.getRoles()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getName()));
        return jwtResponse;
    }

    public JwtResponse refresh(RefreshRequest refreshRequest) {
        return jwtTokenProvider.refreshUserTokens(refreshRequest.getRefreshToken());
    }
}
