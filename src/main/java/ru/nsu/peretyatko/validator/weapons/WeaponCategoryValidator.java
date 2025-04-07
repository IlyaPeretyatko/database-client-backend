package ru.nsu.peretyatko.validator.weapons;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.weapons.WeaponCategoryRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class WeaponCategoryValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(WeaponCategoryRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
