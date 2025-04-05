package ru.nsu.peretyatko.validator.equipments;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
public class EquipmentTypeValidator extends DefaultValidator implements Validator {    @Override
public boolean supports(Class<?> clazz) {
    return clazz.equals(EquipmentTypeRequest.class);
}

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }

}
