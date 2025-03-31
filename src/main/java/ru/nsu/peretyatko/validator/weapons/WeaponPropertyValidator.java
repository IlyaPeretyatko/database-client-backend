package ru.nsu.peretyatko.validator.weapons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPropertyPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
@RequiredArgsConstructor
public class WeaponPropertyValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(WeaponPropertyPostRequest.class) || clazz.equals(WeaponPropertyPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
