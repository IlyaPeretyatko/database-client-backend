package ru.nsu.peretyatko.validator.militaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPatchRequest;
import ru.nsu.peretyatko.dto.militaries.MilitaryPropertyPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
@RequiredArgsConstructor
public class MilitaryPropertyValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MilitaryPropertyPostRequest.class) || clazz.equals(MilitaryPropertyPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
