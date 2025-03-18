package ru.nsu.peretyatko.validator.session;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.nsu.peretyatko.dto.session.SessionRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class SessionValidatorImp extends DefaultValidator implements SessionValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SessionRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }

}
