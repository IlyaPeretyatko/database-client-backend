package ru.nsu.peretyatko.service.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.equipments.EquipmentPatchRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentPostRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.equipments.EquipmentMapper;
import ru.nsu.peretyatko.model.equipments.Equipment;
import ru.nsu.peretyatko.repository.equipments.EquipmentCustomRepository;
import ru.nsu.peretyatko.repository.equipments.EquipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    
    private final EquipmentCustomRepository equipmentCustomRepository;
    
    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentRepository.findAll(pageable).map(equipmentMapper::toEquipmentResponse);
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

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByType(String titleType) {
        return equipmentCustomRepository.findEquipmentsByType(titleType).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByCategory(String titleCategory) {
        return equipmentCustomRepository.findEquipmentsByCategory(titleCategory).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByTypeUnit(String titleType, int unitId) {
        return equipmentCustomRepository.findEquipmentsByTypeUnit(titleType, unitId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByCategoryUnit(String titleCategory, int unitId) {
        return equipmentCustomRepository.findEquipmentsByCategoryUnit(titleCategory, unitId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByTypeDivision(String titleType, int divisionId) {
        return equipmentCustomRepository.findEquipmentsByTypeDivision(titleType, divisionId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByCategoryDivision(String titleCategory, int divisionId) {
        return equipmentCustomRepository.findEquipmentsByCategoryDivision(titleCategory, divisionId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByTypeBrigade(String titleType, int brigadeId) {
        return equipmentCustomRepository.findEquipmentsByTypeBrigade(titleType, brigadeId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByCategoryBrigade(String titleCategory, int brigadeId) {
        return equipmentCustomRepository.findEquipmentsByCategoryBrigade(titleCategory, brigadeId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByTypeCorps(String titleType, int corpsId) {
        return equipmentCustomRepository.findEquipmentsByTypeCorps(titleType, corpsId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByCategoryCorps(String titleCategory, int corpsId) {
        return equipmentCustomRepository.findEquipmentsByCategoryCorps(titleCategory, corpsId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByTypeArmy(String titleType, int armyId) {
        return equipmentCustomRepository.findEquipmentsByTypeArmy(titleType, armyId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<EquipmentResponse> getEquipmentsByCategoryArmy(String titleCategory, int armyId) {
        return equipmentCustomRepository.findEquipmentsByCategoryArmy(titleCategory, armyId).stream().map(equipmentMapper::toEquipmentResponse).toList();
    }
}
