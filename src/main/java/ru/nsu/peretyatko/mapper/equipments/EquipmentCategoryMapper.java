package ru.nsu.peretyatko.mapper.equipments;

import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.equipments.EquipmentCategoryRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentCategoryResponse;
import ru.nsu.peretyatko.model.equipments.EquipmentCategory;

@Component
public class EquipmentCategoryMapper {
    public EquipmentCategory toEquipmentCategory(EquipmentCategoryRequest equipmentCategoryRequest) {
        EquipmentCategory equipmentCategory = new EquipmentCategory();
        equipmentCategory.setTitle(equipmentCategoryRequest.getTitle());
        return equipmentCategory;
    }

    public EquipmentCategoryResponse toEquipmentCategoryResponse(EquipmentCategory equipmentCategory) {
        EquipmentCategoryResponse equipmentCategoryResponse = new EquipmentCategoryResponse();
        equipmentCategoryResponse.setId(equipmentCategory.getId());
        equipmentCategoryResponse.setTitle(equipmentCategory.getTitle());
        return equipmentCategoryResponse;
    }
}
