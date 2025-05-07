package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.ArmyResponse;
import ru.nsu.peretyatko.dto.infrastructure.DivisionResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.ArmyMapper;
import ru.nsu.peretyatko.model.infrastructure.Army;
import ru.nsu.peretyatko.repository.infrastructure.ArmyCustomRepository;
import ru.nsu.peretyatko.repository.infrastructure.ArmyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArmyService {

    private final ArmyCustomRepository armyCustomRepository;

    private final ArmyRepository armyRepository;

    private final ArmyMapper armyMapper;

    @Transactional(readOnly = true)
    public List<ArmyResponse> getArmies() {
        return armyRepository.findAll().stream().map(armyMapper::toArmyResponse).toList();
    }

    @Transactional(readOnly = true)
    public ArmyResponse getArmy(int id) {
        Army army = armyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Army was not found."));
        return armyMapper.toArmyResponse(army);
    }

    @Transactional
    public void createArmy(ArmyPostRequest armyPostRequest) {
        armyRepository.save(armyMapper.toArmy(armyPostRequest));
    }

    @Transactional
    public void updateArmy(int id, ArmyPatchRequest armyPatchRequest) {
        Army army = armyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Army was not found."));
        armyMapper.updateArmy(army, armyPatchRequest);
        armyRepository.save(army);
    }

    @Transactional
    public void deleteArmy(int id) {
        if (!armyRepository.existsById(id)) {
            throw new ServiceException(404, "Army was not found.");
        }
        armyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ArmyResponse getArmyWithMostUnits() {
        return armyMapper.toArmyResponse(armyCustomRepository.findArmyWithMostUnits());
    }

    @Transactional(readOnly = true)
    public ArmyResponse getArmyWithFewestUnits() {
        return armyMapper.toArmyResponse(armyCustomRepository.findArmyWithFewestUnits());
    }

}