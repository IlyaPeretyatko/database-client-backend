package ru.nsu.peretyatko.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.peretyatko.dto.auth.jwt.JwtRequest;
import ru.nsu.peretyatko.dto.auth.jwt.JwtResponse;
import ru.nsu.peretyatko.dto.auth.jwt.RefreshRequest;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.auth.User;
import ru.nsu.peretyatko.security.JwtTokenProvider;


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

    public boolean isTokenValid(String token) {
        return jwtTokenProvider.isValid(token);
    }
}
