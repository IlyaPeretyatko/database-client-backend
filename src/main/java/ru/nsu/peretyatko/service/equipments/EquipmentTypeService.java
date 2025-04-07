package ru.nsu.peretyatko.service.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentTypeResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.equipments.EquipmentTypeMapper;
import ru.nsu.peretyatko.model.equipments.EquipmentType;
import ru.nsu.peretyatko.repository.equipments.EquipmentTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;

    private final EquipmentTypeMapper equipmentTypeMapper;

    @Transactional(readOnly = true)
    public List<EquipmentTypeResponse> getEquipmentTypes() {
        return equipmentTypeRepository.findAll().stream().map(equipmentTypeMapper::toEquipmentTypeResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EquipmentTypeResponse getEquipmentType(int id) {
        EquipmentType equipmentType = equipmentTypeRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment type was not found."));
        return equipmentTypeMapper.toEquipmentTypeResponse(equipmentType);
    }

    @Transactional
    public void createEquipmentType(EquipmentTypeRequest equipmentTypeRequest) {
        equipmentTypeRepository.save(equipmentTypeMapper.toEquipmentType(equipmentTypeRequest));
    }

    @Transactional
    public void deleteEquipmentType(int id) {
        if (!equipmentTypeRepository.existsById(id)) { throw new ServiceException(404, "Equipment type was not found."); }
        equipmentTypeRepository.deleteById(id);
    }
}