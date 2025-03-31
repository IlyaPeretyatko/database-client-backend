package ru.nsu.peretyatko.validator.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPostRequest;
import ru.nsu.peretyatko.validator.DefaultValidator;

@Component
@RequiredArgsConstructor
public class EquipmentPropertyValidator extends DefaultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(EquipmentPropertyPostRequest.class) || clazz.equals(EquipmentPropertyPatchRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
