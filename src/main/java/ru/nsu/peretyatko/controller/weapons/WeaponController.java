package ru.nsu.peretyatko.controller.weapons;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;
import ru.nsu.peretyatko.service.weapons.WeaponService;
import ru.nsu.peretyatko.validator.weapons.WeaponValidator;


@Tag(name = "Weapon API")
@RestController
@RequestMapping("/weapons")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;

    private final WeaponValidator weaponValidator;

    @Operation(summary = "Получить перечень оружия")
    @GetMapping
    public Page<WeaponResponse> getWeapons(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeapons(page, size);
    }

    @Operation(summary = "Получить оружие по ID")
    @GetMapping("/{id}")
    public WeaponResponse getWeaponById(@PathVariable int id) {
        return weaponService.getWeapon(id);
    }

    @Operation(summary = "Добавить оружие")
    @PostMapping
    public void createWeapon(@Valid @RequestBody WeaponPostRequest weaponPostRequest,
                             BindingResult bindingResult) {
        weaponValidator.validate(weaponPostRequest, bindingResult);
        weaponService.createWeapon(weaponPostRequest);
    }

    @Operation(summary = "Изменить данные оружия по ID")
    @PatchMapping("/{id}")
    public void updateWeapon(@PathVariable int id,
                             @Valid @RequestBody WeaponPatchRequest weaponPatchRequest,
                             BindingResult bindingResult) {
        weaponValidator.validate(weaponPatchRequest, bindingResult);
        weaponService.updateWeapon(id, weaponPatchRequest);
    }

    @Operation(summary = "Удалить оружие по ID")
    @DeleteMapping("/{id}")
    public void deleteWeapon(@PathVariable int id) {
        weaponService.deleteWeapon(id);
    }

    @Operation(summary = "Получить перечень оружий по виду")
    @GetMapping("/by/type")
    public Page<WeaponResponse> getWeaponsByType(@RequestParam String title,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByType(title, page, size);
    }

    @Operation(summary = "Получить перечень оружий по категории")
    @GetMapping("/by/category")
    public Page<WeaponResponse> getWeaponsByCategory(@RequestParam String title,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByCategory(title, page, size);
    }

    @Operation(summary = "Получить перечень оружий военной части по виду")
    @GetMapping("/by/type/unit/{id}")
    public Page<WeaponResponse> getWeaponsByTypeUnit(@PathVariable int id,
                                                     @RequestParam String title,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByTypeUnit(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий военной части по категории")
    @GetMapping("/by/category/unit/{id}")
    public Page<WeaponResponse> getWeaponsByCategoryUnit(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByCategoryUnit(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий дивизии по виду")
    @GetMapping("/by/type/division/{id}")
    public Page<WeaponResponse> getWeaponsByTypeDivision(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByTypeDivision(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий дивизии по категории")
    @GetMapping("/by/category/division/{id}")
    public Page<WeaponResponse> getWeaponsByCategoryDivision(@PathVariable int id,
                                                             @RequestParam String title,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByCategoryDivision(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий бригады по виду")
    @GetMapping("/by/type/brigade/{id}")
    public Page<WeaponResponse> getWeaponsByTypeBrigade(@PathVariable int id,
                                                        @RequestParam String title,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByTypeBrigade(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий бригады по категории")
    @GetMapping("/by/category/brigade/{id}")
    public Page<WeaponResponse> getWeaponsByCategoryBrigade(@PathVariable int id,
                                                            @RequestParam String title,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByCategoryBrigade(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий корпуса по виду")
    @GetMapping("/by/type/corps/{id}")
    public Page<WeaponResponse> getWeaponsByTypeCorps(@PathVariable int id,
                                                      @RequestParam String title,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByTypeCorps(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий корпуса по категории")
    @GetMapping("/by/category/corps/{id}")
    public Page<WeaponResponse> getWeaponsByCategoryCorps(@PathVariable int id,
                                                          @RequestParam String title,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByCategoryCorps(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий армии по виду")
    @GetMapping("/by/type/army/{id}")
    public Page<WeaponResponse> getWeaponsByTypeArmy(@PathVariable int id,
                                                     @RequestParam String title,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByTypeArmy(title, id, page, size);
    }

    @Operation(summary = "Получить перечень оружий армии по категории")
    @GetMapping("/by/category/army/{id}")
    public Page<WeaponResponse> getWeaponsByCategoryArmy(@PathVariable int id,
                                                         @RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return weaponService.getWeaponsByCategoryArmy(title, id, page, size);
    }
}