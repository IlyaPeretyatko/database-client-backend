package ru.nsu.peretyatko.validator.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
@RequiredArgsConstructor
public class CompanyValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CompanyPostRequest.class) || clazz.equals(CompanyPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
