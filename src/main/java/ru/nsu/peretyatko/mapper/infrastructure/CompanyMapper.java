package ru.nsu.peretyatko.mapper.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.infrastructure.Company;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.repository.infrastructure.UnitRepository;
import ru.nsu.peretyatko.repository.militaries.MilitaryRepository;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    private final MilitaryRepository militaryRepository;

    private final UnitRepository unitRepository;

    public Company toCompany(CompanyPostRequest companyPostRequest) {
        Company company = new Company();
        company.setTitle(companyPostRequest.getTitle());
        Military military = militaryRepository.findById(companyPostRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
        company.setCommander(military);
        Unit unit = unitRepository.findById(companyPostRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit eas not found."));
        company.setUnit(unit);
        return company;
    }

    public CompanyResponse toCompanyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setTitle(company.getTitle());
        companyResponse.setCommanderId(company.getCommander().getId());
        companyResponse.setUnitId(company.getUnit().getId());
        return companyResponse;
    }

    public void updateCompany(Company company, CompanyPatchRequest companyPatchRequest) {
        if (companyPatchRequest.getTitle() != null) {
            company.setTitle(companyPatchRequest.getTitle());
        }
        if (companyPatchRequest.getCommanderId() != null) {
            Military military = militaryRepository.findById(companyPatchRequest.getCommanderId()).orElseThrow(() -> new ServiceException(404, "Commander was not found."));
            company.setCommander(military);
        }
        if (companyPatchRequest.getUnitId() != null) {
            Unit unit = unitRepository.findById(companyPatchRequest.getUnitId()).orElseThrow(() -> new ServiceException(404, "Unit eas not found."));
            company.setUnit(unit);
        }
    }
}
