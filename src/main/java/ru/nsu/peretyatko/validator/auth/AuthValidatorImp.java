package ru.nsu.peretyatko.validator.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.nsu.peretyatko.dto.auth.JwtRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;


@Component
@RequiredArgsConstructor
public class AuthValidatorImp extends DefaultValidator implements AuthValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(JwtRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}