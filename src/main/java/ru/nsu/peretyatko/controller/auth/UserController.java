package ru.nsu.peretyatko.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.auth.user.UserPatchRequest;
import ru.nsu.peretyatko.dto.auth.user.UserPostRequest;
import ru.nsu.peretyatko.dto.auth.user.UserPostResponse;
import ru.nsu.peretyatko.dto.user.*;
import ru.nsu.peretyatko.service.UserService;
import ru.nsu.peretyatko.validator.auth.UserValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        boolean isVerified = userService.verifyEmail(token);
        if (isVerified) {
            return "Email verification successful!";
        }
        return "Invalid verification token.";
    }

    @PostMapping
    public UserPostResponse createUser(@Valid @RequestBody UserPostRequest userPostRequest,
                                       BindingResult bindingResult) {
        userValidator.validate(userPostRequest, bindingResult);
        return userService.createUser(userPostRequest);
    }

    @GetMapping("/request-reset-password")
    public void requestResetPassword(@RequestParam String email) {
        userService.requestResetPassword(email);
    }

    @PatchMapping("/reset-password")
    public void resetPasswordForm(@RequestParam String token,
                                  @RequestBody UserPatchRequest userPatchRequest,
                                  BindingResult bindingResult) {
        userValidator.validate(userPatchRequest, bindingResult);
        userService.resetPassword(token, userPatchRequest);
    }

    

}
