package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.PlatoonResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.buildings.BuildingMapper;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.model.infrastructure.Platoon;
import ru.nsu.peretyatko.model.infrastructure.Company;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.buildngs.BuildingRepository;
import ru.nsu.peretyatko.repository.infrastructure.CompanyRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlatoonMapper {
    private final MilitaryRepository militaryRepository;

    private final CompanyRepository companyRepository;

    private final BuildingRepository buildingRepository;

    private final BuildingMapper buildingMapper;

    public Platoon toPlatoon(PlatoonPostRequest platoonPostRequest) {
        Platoon platoon = new Platoon();
        platoon.setTitle(platoonPostRequest.getTitle());
        Military military = militaryRepository.findById(platoonPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        platoon.setCommander(military);
        Company company = companyRepository.findById(platoonPostRequest.getCompanyId()).orElseThrow(() -> new ServiceException(404, "Company eas not found."));
        platoon.setCompany(company);
        Set<Building> buildings = platoonPostRequest.getBuildingsId().stream()
                .map(id -> buildingRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Building with id:" + id + " was not found.")))
                .collect(Collectors.toSet());
        platoon.setBuildings(buildings);
        return platoon;
    }

    public PlatoonResponse toPlatoonResponse(Platoon platoon) {
        PlatoonResponse platoonResponse = new PlatoonResponse();
        platoonResponse.setId(platoon.getId());
        platoonResponse.setTitle(platoon.getTitle());
        platoonResponse.setCommanderId(platoon.getCommander().getId());
        platoonResponse.setCompanyId(platoon.getCompany().getId());
        platoonResponse.setBuildings(platoon.getBuildings().stream().map(buildingMapper::toBuildingResponse).collect(Collectors.toSet()));
        return platoonResponse;
    }

    public void updatePlatoon(Platoon platoon, PlatoonPatchRequest platoonPatchRequest) {
        if (platoonPatchRequest.getTitle() != null) {
            platoon.setTitle(platoonPatchRequest.getTitle());
        }
        if (platoonPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(platoonPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            platoon.setCommander(military);
        }
        if (platoonPatchRequest.getCompanyId() != null) {
            Company company = companyRepository.findById(platoonPatchRequest.getCompanyId()).orElseThrow(() -> new ServiceException(404, "Company eas not found."));
            platoon.setCompany(company);
        }
        if (platoonPatchRequest.getBuildingsId() != null) {
            Set<Building> buildings = platoonPatchRequest.getBuildingsId().stream()
                    .map(id -> buildingRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Building with id:" + id + " was not found.")))
                    .collect(Collectors.toSet());
            platoon.setBuildings(buildings);
        }
    }
}
