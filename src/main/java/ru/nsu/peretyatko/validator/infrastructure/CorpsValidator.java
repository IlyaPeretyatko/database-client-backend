package ru.nsu.peretyatko.validator.infrastructure;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CorpsPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

public class CorpsValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CorpsPostRequest.class) || clazz.equals(CorpsPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
