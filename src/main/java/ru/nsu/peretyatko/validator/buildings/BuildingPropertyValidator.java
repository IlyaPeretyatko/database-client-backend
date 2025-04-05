package ru.nsu.peretyatko.validator.buildings;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPatchRequest;
import ru.nsu.peretyatko.dto.buildings.BuildingPropertyPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class BuildingPropertyValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(BuildingPropertyPostRequest.class) || clazz.equals(BuildingPropertyPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
