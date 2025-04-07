package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.UnitPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.UnitResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.equipments.EquipmentMapper;
import ru.nsu.peretyatko.mapper.weapons.WeaponMapper;
import ru.nsu.peretyatko.model.equipments.Equipment;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.weapons.Weapon;
import ru.nsu.peretyatko.repository.equipments.EquipmentRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;
import ru.nsu.peretyatko.repository.weapons.WeaponRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UnitMapper {

    private final MilitaryRepository militaryRepository;

    private final WeaponRepository weaponRepository;

    private final EquipmentRepository equipmentRepository;

    private final WeaponMapper weaponMapper;

    private final EquipmentMapper equipmentMapper;

    public Unit toUnit(UnitPostRequest unitPostRequest) {
        Unit unit = new Unit();
        unit.setTitle(unitPostRequest.getTitle());
        unit.setLatitude(unitPostRequest.getLatitude());
        unit.setLongitude(unitPostRequest.getLongitude());
        Military military = militaryRepository.findById(unitPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        unit.setCommander(military);
        if (unitPostRequest.getWeaponsId() != null) {
            Set<Weapon> weapons = unitPostRequest.getWeaponsId().stream()
                    .map(id -> weaponRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            unit.setWeapons(weapons);
        }
        if (unitPostRequest.getEquipmentsId() != null) {
            Set<Equipment> equipments = unitPostRequest.getEquipmentsId().stream()
                    .map(id -> equipmentRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            unit.setEquipments(equipments);
        }
        return unit;
    }

    public UnitResponse toUnitResponse(Unit unit) {
        UnitResponse unitResponse = new UnitResponse();
        unitResponse.setId(unit.getId());
        unitResponse.setTitle(unit.getTitle());
        unitResponse.setLatitude(unit.getLatitude());
        unitResponse.setLongitude(unit.getLongitude());
        unitResponse.setCommanderId(unit.getCommander().getId());
        unitResponse.setWeapons(unit.getWeapons().stream().map(weaponMapper::toWeaponResponse).collect(Collectors.toSet()));
        unitResponse.setEquipments(unit.getEquipments().stream().map(equipmentMapper::toEquipmentResponse).collect(Collectors.toSet()));
        return unitResponse;
    }

    public void updateUnit(Unit unit, UnitPatchRequest unitPatchRequest) {
        if (unitPatchRequest.getTitle() != null) {
            unit.setTitle(unitPatchRequest.getTitle());
        }
        if (unitPatchRequest.getLatitude() != null) {
            unit.setLatitude(unitPatchRequest.getLatitude());
        }
        if (unitPatchRequest.getLongitude() != null) {
            unit.setLongitude(unitPatchRequest.getLongitude());
        }
        if (unitPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(unitPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander id was not found."));
            unit.setCommander(military);
        }
        if (unitPatchRequest.getWeaponsId() != null) {
            Set<Weapon> weapons = unitPatchRequest.getWeaponsId().stream()
                    .map(id -> weaponRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Weapon with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            unit.setWeapons(weapons);
        }
        if (unitPatchRequest.getEquipmentsId() != null) {
            Set<Equipment> equipments = unitPatchRequest.getEquipmentsId().stream()
                    .map(id -> equipmentRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Equipment with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            unit.setEquipments(equipments);
        }
    }
}
