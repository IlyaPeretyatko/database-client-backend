package ru.nsu.peretyatko.service.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.equipments.EquipmentPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.equipments.EquipmentMapper;
import ru.nsu.peretyatko.model.equipments.Equipment;
import ru.nsu.peretyatko.repository.equipments.EquipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipments() {
        return equipmentRepository.findAll().stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public EquipmentResponse getEquipment(int id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment was not found."));
        return equipmentMapper.toEquipmentResponse(equipment);
    }

    @Transactional
    public void createEquipment(EquipmentPostRequest equipmentPostRequest) {
        equipmentRepository.save(equipmentMapper.toEquipment(equipmentPostRequest));
    }

    @Transactional
    public void updateEquipment(int id, EquipmentPatchRequest equipmentPatchRequest) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment was not found."));
        equipmentMapper.updateEquipment(equipment, equipmentPatchRequest);
        equipmentRepository.save(equipment);
    }

    @Transactional
    public void deleteEquipment(int id) {
        if (!equipmentRepository.existsById(id)) {
            throw new ServiceException(404, "Equipment was not found.");
        }
        equipmentRepository.deleteById(id);
    }
}
