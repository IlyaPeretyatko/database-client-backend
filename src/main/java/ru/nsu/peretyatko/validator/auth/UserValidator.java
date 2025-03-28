package ru.nsu.peretyatko.validator.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.auth.user.UserPostRequest;
import ru.nsu.peretyatko.error.exception.ValidationException;
import ru.nsu.peretyatko.service.auth.UserService;
import ru.nsu.peretyatko.validator.DefaultValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidator extends DefaultValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserPostRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
        if (target instanceof UserPostRequest userPostRequest) {
            if (userService.existsByEmail(userPostRequest.getEmail())) {
                throw new ValidationException("User with this email already exists", List.of("email"));
            } else if (userService.existsByName(userPostRequest.getName())) {
                throw new ValidationException("User with this name already exists", List.of("name"));
            }
        }
    }
}
