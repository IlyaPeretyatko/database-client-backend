package ru.nsu.peretyatko.controller.auth;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.auth.user.UserPatchRequest;
import ru.nsu.peretyatko.dto.auth.user.UserPostRequest;
import ru.nsu.peretyatko.dto.auth.user.UserPostResponse;
import ru.nsu.peretyatko.service.auth.UserService;
import ru.nsu.peretyatko.validator.auth.UserValidator;

import jakarta.validation.Valid;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    @Operation(summary = "Подтвердить Email")
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        boolean isVerified = userService.verifyEmail(token);
        if (isVerified) {
            return "Email verification successful!";
        }
        return "Invalid verification token.";
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public UserPostResponse createUser(@Valid @RequestBody UserPostRequest userPostRequest,
                                       BindingResult bindingResult) {
        userValidator.validate(userPostRequest, bindingResult);
        return userService.createUser(userPostRequest);
    }

    @Operation(summary = "Отправить запрос на восстановление пароля")
    @GetMapping("/request-reset-password")
    public void requestResetPassword(@RequestParam String email) {
        userService.requestResetPassword(email);
    }

    @Operation(summary = "Восстановить пароль")
    @PatchMapping("/reset-password")
    public void resetPasswordForm(@RequestParam String token,
                                  @RequestBody UserPatchRequest userPatchRequest,
                                  BindingResult bindingResult) {
        userValidator.validate(userPatchRequest, bindingResult);
        userService.resetPassword(token, userPatchRequest);
    }


}
