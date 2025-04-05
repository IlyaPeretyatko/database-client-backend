package ru.nsu.peretyatko.controller.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPatchRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyPostRequest;
import ru.nsu.peretyatko.dto.infrastructure.CompanyResponse;
import ru.nsu.peretyatko.service.infrastructure.CompanyService;
import ru.nsu.peretyatko.validator.infrastructure.CompanyValidator;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyValidator companyValidator;

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @PostMapping
    public void createCompany(@Valid @RequestBody CompanyPostRequest companyPostRequest,
                           BindingResult bindingResult) {
        companyValidator.validate(companyPostRequest, bindingResult);
        companyService.createCompany(companyPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateCompany(@PathVariable int id,
                           @Valid @RequestBody CompanyPatchRequest companyPatchRequest,
                           BindingResult bindingResult) {
        companyValidator.validate(companyPatchRequest, bindingResult);
        companyService.updateCompany(id, companyPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
