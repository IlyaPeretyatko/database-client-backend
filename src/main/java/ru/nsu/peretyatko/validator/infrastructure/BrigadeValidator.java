package ru.nsu.peretyatko.validator.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.BrigadePostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
@RequiredArgsConstructor
public class BrigadeValidator extends DefaultValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(BrigadePostRequest.class) || clazz.equals(BrigadePatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }

}
