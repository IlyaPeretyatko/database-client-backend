package ru.nsu.peretyatko.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.peretyatko.dto.auth.role.RoleRequest;
import ru.nsu.peretyatko.dto.auth.role.RoleResponse;
import ru.nsu.peretyatko.service.auth.RoleService;
import ru.nsu.peretyatko.service.auth.UserService;

import java.util.List;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

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

    @Operation(summary = "Добавить роль пользователю")
    @PatchMapping("/user/{id}")
    public void addRoleForUser(@PathVariable long id, @RequestParam String title) {
        userService.addRoleForUser(id, title);
    }

    @Operation(summary = "Удалить роль пользователю")
    @DeleteMapping("/user/{id}")
    public void deleteRoleForUser(@PathVariable long id, @RequestParam String title) {
        userService.deleteRoleForUser(id, title);
    }

}
