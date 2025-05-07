package ru.nsu.peretyatko.controller.weapons;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.weapons.WeaponPatchRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponPostRequest;
import ru.nsu.peretyatko.dto.weapons.WeaponResponse;
import ru.nsu.peretyatko.service.weapons.WeaponService;
import ru.nsu.peretyatko.validator.weapons.WeaponValidator;

import java.util.List;

@RestController
@RequestMapping("/weapons")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;

    private final WeaponValidator weaponValidator;

    @GetMapping
    public List<WeaponResponse> getWeapons() {
        return weaponService.getWeapons();
    }

    @GetMapping("/{id}")
    public WeaponResponse getWeaponById(@PathVariable int id) {
        return weaponService.getWeapon(id);
    }

    @PostMapping
    public void createWeapon(@Valid @RequestBody WeaponPostRequest weaponPostRequest,
                                BindingResult bindingResult) {
        weaponValidator.validate(weaponPostRequest, bindingResult);
        weaponService.createWeapon(weaponPostRequest);
    }

    @PatchMapping("/{id}")
    public void updateWeapon(@PathVariable int id,
                                @Valid @RequestBody WeaponPatchRequest weaponPatchRequest,
                                BindingResult bindingResult) {
        weaponValidator.validate(weaponPatchRequest, bindingResult);
        weaponService.updateWeapon(id, weaponPatchRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWeapon(@PathVariable int id) {
        weaponService.deleteWeapon(id);
    }

    @GetMapping("/by/type")
    public List<WeaponResponse> getWeaponsByType(@RequestParam String title) {
        return weaponService.getWeaponsByType(title);
    }

    @GetMapping("/by/category")
    public List<WeaponResponse> getWeaponsByCategory(@RequestParam String title) {
        return weaponService.getWeaponsByCategory(title);
    }

    @GetMapping("/unit/{id}/by/type")
    public List<WeaponResponse> getWeaponsByTypeUnit(@PathVariable int id,
                                                     @RequestParam String title) {
        return weaponService.getWeaponsByTypeUnit(title,id);
    }

    @GetMapping("/unit/{id}/by/category")
    public List<WeaponResponse> getWeaponsByCategoryUnit(@PathVariable int id,
                                                         @RequestParam String title) {
        return weaponService.getWeaponsByCategoryUnit(title,id);
    }

    @GetMapping("/division/{id}/by/type")
    public List<WeaponResponse> getWeaponsByTypeDivision(@PathVariable int id,
                                                         @RequestParam String title) {
        return weaponService.getWeaponsByTypeDivision(title,id);
    }

    @GetMapping("/division/{id}/by/category")
    public List<WeaponResponse> getWeaponsByCategoryDivision(@PathVariable int id,
                                                             @RequestParam String title) {
        return weaponService.getWeaponsByCategoryDivision(title,id);
    }

    @GetMapping("/brigade/{id}/by/type")
    public List<WeaponResponse> getWeaponsByTypeBrigade(@PathVariable int id,
                                                        @RequestParam String title) {
        return weaponService.getWeaponsByTypeBrigade(title,id);
    }

    @GetMapping("/brigade/{id}/by/category")
    public List<WeaponResponse> getWeaponsByCategoryBrigade(@PathVariable int id,
                                                            @RequestParam String title) {
        return weaponService.getWeaponsByCategoryBrigade(title,id);
    }

    @GetMapping("/corps/{id}/by/type")
    public List<WeaponResponse> getWeaponsByTypeCorps(@PathVariable int id,
                                                      @RequestParam String title) {
        return weaponService.getWeaponsByTypeCorps(title, id);
    }

    @GetMapping("/corps/{id}/by/category")
    public List<WeaponResponse> getWeaponsByCategoryCorps(@PathVariable int id,
                                                          @RequestParam String title) {
        return weaponService.getWeaponsByCategoryCorps(title, id);
    }

    @GetMapping("/army/{id}/by/type")
    public List<WeaponResponse> getWeaponsByTypeArmy(@PathVariable int id,
                                                     @RequestParam String title) {
        return weaponService.getWeaponsByTypeArmy(title, id);
    }

    @GetMapping("/army/{id}/by/category")
    public List<WeaponResponse> getWeaponsByCategoryArmy(@PathVariable int id,
                                                         @RequestParam String title) {
        return weaponService.getWeaponsByCategoryArmy(title, id);
    }
}