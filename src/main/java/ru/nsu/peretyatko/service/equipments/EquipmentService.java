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
    public Page<EquipmentResponse> getEquipmentsByType(String titleType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByType(titleType, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByCategory(String titleCategory, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByCategory(titleCategory, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByTypeUnit(String titleType, int unitId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByTypeUnit(titleType, unitId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByCategoryUnit(String titleCategory, int unitId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByCategoryUnit(titleCategory, unitId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByTypeDivision(String titleType, int divisionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByTypeDivision(titleType, divisionId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByCategoryDivision(String titleCategory, int divisionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByCategoryDivision(titleCategory, divisionId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByTypeBrigade(String titleType, int brigadeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByTypeBrigade(titleType, brigadeId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByCategoryBrigade(String titleCategory, int brigadeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByCategoryBrigade(titleCategory, brigadeId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByTypeCorps(String titleType, int corpsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByTypeCorps(titleType, corpsId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByCategoryCorps(String titleCategory, int corpsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByCategoryCorps(titleCategory, corpsId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByTypeArmy(String titleType, int armyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByTypeArmy(titleType, armyId, pageable).map(equipmentMapper::toEquipmentResponse);
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponse> getEquipmentsByCategoryArmy(String titleCategory, int armyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCustomRepository.findEquipmentsByCategoryArmy(titleCategory, armyId, pageable).map(equipmentMapper::toEquipmentResponse);
    }
}
