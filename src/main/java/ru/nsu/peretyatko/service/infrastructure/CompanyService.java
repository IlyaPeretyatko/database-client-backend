package ru.nsu.peretyatko.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.infrastructure.CompanyMapper;
import ru.nsu.peretyatko.model.infrastructure.Company;
import ru.nsu.peretyatko.repository.infrastructure.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Transactional(readOnly = true)
    public List<CompanyResponse> getMilitaries() {
        return companyRepository.findAll().stream().map(companyMapper::toCompanyResponse).toList();
    }

    @Transactional(readOnly = true)
    public CompanyResponse getCompany(int id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Company was not found."));
        return companyMapper.toCompanyResponse(company);
    }

    @Transactional
    public void createCompany(CompanyPostRequest companyPostRequest) {
        companyRepository.save(companyMapper.toCompany(companyPostRequest));
    }

    @Transactional
    public void updateCompany(int id, CompanyPatchRequest companyPatchRequest) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Company was not found."));
        companyMapper.updateCompany(company, companyPatchRequest);
        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(int id) {
        if (!companyRepository.existsById(id)) {
            throw new ServiceException(404, "Company was not found.");
        }
        companyRepository.deleteById(id);
    }
}
