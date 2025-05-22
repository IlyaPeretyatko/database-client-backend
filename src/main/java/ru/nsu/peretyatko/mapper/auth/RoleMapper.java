package ru.nsu.peretyatko.mapper.auth;

import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.auth.role.RoleRequest;
import ru.nsu.peretyatko.dto.auth.role.RoleResponse;
import ru.nsu.peretyatko.model.auth.Role;

@Component
public class RoleMapper {


    public RoleResponse toRoleResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setTitle(role.getTitle());
        return roleResponse;
    }

    public Role toRole(RoleRequest roleRequest) {
        return new Role(roleRequest.getTitle());
    }
}
