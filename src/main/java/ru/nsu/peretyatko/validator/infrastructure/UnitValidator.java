package ru.nsu.peretyatko.validator.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class UnitValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UnitPostRequest.class) || clazz.equals(UnitPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
