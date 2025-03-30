package ru.nsu.peretyatko.validator.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
@RequiredArgsConstructor
public class ArmyValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ArmyPostRequest.class) || clazz.equals(ArmyPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
