package ru.nsu.peretyatko.controller.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Separations of units API")
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyValidator companyValidator;

    @Operation(summary = "Получить перечень рот")
    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.getCompanies();
    }

    @Operation(summary = "Получить роту по ID")
    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @Operation(summary = "Добавить роту")
    @PostMapping
    public void createCompany(@Valid @RequestBody CompanyPostRequest companyPostRequest,
                           BindingResult bindingResult) {
        companyValidator.validate(companyPostRequest, bindingResult);
        companyService.createCompany(companyPostRequest);
    }

    @Operation(summary = "Изменить данные роты по ID")
    @PatchMapping("/{id}")
    public void updateCompany(@PathVariable int id,
                           @Valid @RequestBody CompanyPatchRequest companyPatchRequest,
                           BindingResult bindingResult) {
        companyValidator.validate(companyPatchRequest, bindingResult);
        companyService.updateCompany(id, companyPatchRequest);
    }

    @Operation(summary = "Удалить роту по ID")
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
