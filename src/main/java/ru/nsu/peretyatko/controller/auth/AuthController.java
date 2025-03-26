package ru.nsu.peretyatko.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.peretyatko.dto.auth.JwtRequest;
import ru.nsu.peretyatko.dto.auth.JwtResponse;
import ru.nsu.peretyatko.dto.auth.RefreshRequest;
import ru.nsu.peretyatko.service.AuthService;
import ru.nsu.peretyatko.validator.auth.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthValidator authValidator;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody JwtRequest jwtRequest,
                             BindingResult bindingResult) {
        authValidator.validate(jwtRequest, bindingResult);
        return authService.login(jwtRequest);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody RefreshRequest refreshRequest) {
        return authService.refresh(refreshRequest);
    }

}
