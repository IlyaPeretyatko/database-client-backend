package ru.nsu.peretyatko.mapper.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.equipments.EquipmentCategory;
import ru.nsu.peretyatko.model.equipments.EquipmentType;
import ru.nsu.peretyatko.repository.equipments.EquipmentCategoryRepository;

@Component
@RequiredArgsConstructor
public class EquipmentTypeMapper {

    private final EquipmentCategoryRepository equipmentCategoryRepository;

    public EquipmentType toEquipmentType(EquipmentTypeRequest equipmentTypeRequest) {
        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setTitle(equipmentTypeRequest.getTitle());
        EquipmentCategory equipmentCategory = equipmentCategoryRepository.findById(equipmentTypeRequest.getCategoryId()).orElseThrow(() -> new ServiceException(404, "Equipment category was not found."));
        equipmentType.setCategory(equipmentCategory);
        return equipmentType;
    }

    public EquipmentTypeResponse toEquipmentTypeResponse(EquipmentType equipmentType) {
        EquipmentTypeResponse equipmentTypeResponse = new EquipmentTypeResponse();
        equipmentTypeResponse.setId(equipmentType.getId());
        equipmentTypeResponse.setTitle(equipmentType.getTitle());
        equipmentTypeResponse.setCategoryId(equipmentType.getCategory().getId());
        return equipmentTypeResponse;
    }
}
