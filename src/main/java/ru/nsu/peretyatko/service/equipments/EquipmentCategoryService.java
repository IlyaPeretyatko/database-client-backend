package ru.nsu.peretyatko.service.equipments;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.equipments.EquipmentCategoryRequest;
import ru.nsu.peretyatko.dto.equipments.EquipmentCategoryResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.equipments.EquipmentCategoryMapper;
import ru.nsu.peretyatko.model.equipments.EquipmentCategory;
import ru.nsu.peretyatko.repository.equipments.EquipmentCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryService {

    private final EquipmentCategoryRepository equipmentCategoryRepository;

    private final EquipmentCategoryMapper equipmentCategoryMapper;

    @Transactional(readOnly = true)
    public Page<EquipmentCategoryResponse> getEquipmentCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return equipmentCategoryRepository.findAll(pageable).map(equipmentCategoryMapper::toEquipmentCategoryResponse);
    }

    @Transactional(readOnly = true)
    public EquipmentCategoryResponse getEquipmentCategory(int id) {
        EquipmentCategory equipmentCategory = equipmentCategoryRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment category was not found."));
        return equipmentCategoryMapper.toEquipmentCategoryResponse(equipmentCategory);
    }

    @Transactional
    public void createEquipmentCategory(EquipmentCategoryRequest equipmentCategoryRequest) {
        equipmentCategoryRepository.save(equipmentCategoryMapper.toEquipmentCategory(equipmentCategoryRequest));
    }

    @Transactional
    public void deleteEquipmentCategory(int id) {
        if (!equipmentCategoryRepository.existsById(id)) { throw new ServiceException(404, "Equipment category was not found."); }
        equipmentCategoryRepository.deleteById(id);
    }
}