package ru.nsu.peretyatko.mapper.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.equipments.EquipmentPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.equipments.Equipment;
import ru.nsu.peretyatko.model.equipments.EquipmentType;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.repository.equipments.EquipmentTypeRepository;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;

@Component
@RequiredArgsConstructor
public class EquipmentMapper {

    private final EquipmentTypeRepository equipmentTypeRepository;

    private final UnitRepository unitRepository;

    public Equipment toEquipment(EquipmentPostRequest equipmentPostRequest) {
        Equipment equipment = new Equipment();
        equipment.setSerialNumber(equipmentPostRequest.getSerialNumber());
        EquipmentType equipmentType = equipmentTypeRepository.findById(equipmentPostRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Equipment type was not found."));
        equipment.setType(equipmentType);
        Unit unit = unitRepository.findById(equipmentPostRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
        equipment.setUnit(unit);
        return equipment;
    }

    public EquipmentResponse toEquipmentResponse(Equipment equipment) {
        EquipmentResponse equipmentResponse = new EquipmentResponse();
        equipmentResponse.setId(equipment.getId());
        equipmentResponse.setSerialNumber(equipment.getSerialNumber());
        equipmentResponse.setTypeId(equipment.getType().getId());
        equipmentResponse.setUnitId(equipment.getUnit().getId());
        return equipmentResponse;
    }

    public void updateEquipment(Equipment equipment, EquipmentPatchRequest equipmentPatchRequest) {
        if (equipmentPatchRequest.getSerialNumber() != null) {
            equipment.setSerialNumber(equipmentPatchRequest.getSerialNumber());
        }
        if (equipmentPatchRequest.getTypeId() != null) {
            EquipmentType equipmentType = equipmentTypeRepository.findById(equipmentPatchRequest.getTypeId()).orElseThrow(() -> new ServiceException(404, "Equipment type was not found."));
            equipment.setType(equipmentType);
        }
        if (equipmentPatchRequest.getUnitId() != null) {
            Unit unit = unitRepository.findById(equipmentPatchRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit was not found."));
            equipment.setUnit(unit);
        }
    }
}
