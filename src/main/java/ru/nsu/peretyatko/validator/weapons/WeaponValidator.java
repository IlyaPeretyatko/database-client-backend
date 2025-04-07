package ru.nsu.peretyatko.validator.weapons;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class WeaponValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(WeaponPostRequest.class) || clazz.equals(WeaponPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
