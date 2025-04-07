package ru.nsu.peretyatko.validator.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.DivisionPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class DivisionValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DivisionPostRequest.class) || clazz.equals(DivisionPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
