package ru.nsu.peretyatko.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.auth.role.RoleRequest;
import ru.nsu.peretyatko.dto.auth.role.RoleResponse;
import ru.nsu.peretyatko.service.auth.RoleService;

import java.util.List;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "Получить все роли")
    @GetMapping
    public List<RoleResponse> getRoles() {
        return roleService.getRoles();
    }

    @Operation(summary = "Получить конкретную роль")
    @GetMapping("/{id}")
    public RoleResponse getRole(@PathVariable int id) {
        return roleService.getRole(id);
    }

    @Operation(summary = "Создать роль")
    @PostMapping
    public void createRole(@RequestBody RoleRequest roleRequest) {
        roleService.createRole(roleRequest);
    }

    @Operation(summary = "Обновить роль")
    @PatchMapping("/{id}")
    public void updateRole(@PathVariable int id,
                           @RequestBody RoleRequest roleRequest) {
        roleService.updateRole(id, roleRequest);
    }

    @Operation(summary = "Удалить роль")
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
    }

}
