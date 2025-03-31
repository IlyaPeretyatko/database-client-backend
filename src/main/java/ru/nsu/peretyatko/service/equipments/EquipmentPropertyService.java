package ru.nsu.peretyatko.service.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPropertyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.equipments.EquipmentPropertyMapper;
import ru.nsu.peretyatko.model.equipments.EquipmentProperty;
import ru.nsu.peretyatko.repository.equipments.EquipmentPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentPropertyService {
    private final EquipmentPropertyRepository equipmentPropertyRepository;

    private final EquipmentPropertyMapper equipmentPropertyMapper;

    @Transactional(readOnly = true)
    public List<EquipmentPropertyResponse> getEquipmentProperties() {
        return equipmentPropertyRepository.findAll().stream().map(equipmentPropertyMapper::toEquipmentPropertyResponse).toList();
    }

    @Transactional(readOnly = true)
    public EquipmentPropertyResponse getEquipmentProperty(int id) {
        EquipmentProperty equipmentProperty = equipmentPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment property was not found."));
        return equipmentPropertyMapper.toEquipmentPropertyResponse(equipmentProperty);
    }

    @Transactional
    public void createEquipmentProperty(EquipmentPropertyPostRequest equipmentPropertyPostRequest) {
        equipmentPropertyRepository.save(equipmentPropertyMapper.toEquipmentProperty(equipmentPropertyPostRequest));
    }

    @Transactional
    public void updateEquipmentProperty(int id, EquipmentPropertyPatchRequest equipmentPropertyPatchRequest) {
        EquipmentProperty equipmentProperty = equipmentPropertyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment property was not found."));
        equipmentPropertyMapper.updateEquipmentProperty(equipmentProperty, equipmentPropertyPatchRequest);
        equipmentPropertyRepository.save(equipmentProperty);
    }

    @Transactional
    public void deleteEquipmentProperty(int id) {
        if (!equipmentPropertyRepository.existsById(id)) {
            throw new ServiceException(404, "Equipment property was not found.");
        }
        equipmentPropertyRepository.deleteById(id);
    }
}
