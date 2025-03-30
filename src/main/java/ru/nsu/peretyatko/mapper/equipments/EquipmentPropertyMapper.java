package ru.nsu.peretyatko.mapper.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.equipments.EquipmentProperty;
import ru.nsu.peretyatko.model.equipments.EquipmentType;
import ru.nsu.peretyatko.repository.equipments.EquipmentTypeRepository;

@Component
@RequiredArgsConstructor
public class EquipmentPropertyMapper {

    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentProperty toEquipmentProperty(EquipmentPropertyPostRequest equipmentPropertyPostRequest) {
        EquipmentProperty equipmentProperty = new EquipmentProperty();
        equipmentProperty.setTitle(equipmentPropertyPostRequest.getTitle());
        equipmentProperty.setValue(equipmentPropertyPostRequest.getValue());
        EquipmentType equipmentType = equipmentTypeRepository.findById(equipmentPropertyPostRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Equipment type was not found."));
        equipmentProperty.setType(equipmentType);
        return equipmentProperty;
    }

    public EquipmentPropertyResponse toEquipmentPropertyResponse(EquipmentProperty equipmentProperty) {
        EquipmentPropertyResponse equipmentPropertyResponse = new EquipmentPropertyResponse();
        equipmentPropertyResponse.setId(equipmentProperty.getId());
        equipmentPropertyResponse.setTitle(equipmentProperty.getTitle());
        equipmentPropertyResponse.setValue(equipmentProperty.getValue());
        equipmentPropertyResponse.setTypeId(equipmentProperty.getType().getId());
        return equipmentPropertyResponse;
    }

    public void updateEquipmentProperty(EquipmentProperty equipmentProperty, EquipmentPropertyPatchRequest equipmentPropertyPatchRequest) {
        if (equipmentPropertyPatchRequest.getTitle() != null) {
            equipmentProperty.setTitle(equipmentPropertyPatchRequest.getTitle());
        }
        if (equipmentPropertyPatchRequest.getValue() != null) {
            equipmentProperty.setValue(equipmentPropertyPatchRequest.getValue());
        }
        if (equipmentPropertyPatchRequest.getTypeId() != null) {
            EquipmentType equipmentType = equipmentTypeRepository.findById(equipmentPropertyPatchRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Equipment type was not found."));
            equipmentProperty.setType(equipmentType);
        }
    }
}
