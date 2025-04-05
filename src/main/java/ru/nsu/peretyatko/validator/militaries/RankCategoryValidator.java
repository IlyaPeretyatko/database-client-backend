package ru.nsu.peretyatko.validator.militaries;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.militaries.RankCategoryRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class RankCategoryValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RankCategoryRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}