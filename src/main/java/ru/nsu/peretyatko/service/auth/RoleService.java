package ru.nsu.peretyatko.service.auth;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.dto.auth.role.RoleRequest;
import ru.nsu.peretyatko.dto.auth.role.RoleResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.auth.RoleMapper;
import ru.nsu.peretyatko.model.auth.Role;
import ru.nsu.peretyatko.model.militaries.RankCategory;
import ru.nsu.peretyatko.repository.auth.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @PostConstruct
    public void init() {
        if (!roleRepository.existsByTitle("ROLE_ADMIN")) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if (!roleRepository.existsByTitle("ROLE_USER")) {
            roleRepository.save(new Role("ROLE_USER"));
        }
    }

    @Transactional(readOnly = true)
    public List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Transactional(readOnly = true)
    public RoleResponse getRole(int id) {
        return roleMapper.toRoleResponse(roleRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Role wasn't found.")));
    }

    @Transactional
    public void createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);
        if (roleRepository.existsByTitle(roleRequest.getTitle())) {
            throw new ServiceException(404, "Role already exists.");
        }
        roleRepository.save(role);
    }

    @Transactional
    public void updateRole(int id, RoleRequest roleRequest) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Role wasn't found."));
        if (role.getTitle().equals("ROLE_ADMIN") || role.getTitle().equals("ROLE_USER")) {
            throw new ServiceException(409, "Default role cannot be updated.");
        }
        role.setTitle(roleRequest.getTitle());
        if (roleRepository.existsByTitle(roleRequest.getTitle())) {
            throw new ServiceException(404, "Role already exists.");
        }
        roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(int id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ServiceException(404, "Role wasn't found."));
        if (role.getTitle().equals("ROLE_ADMIN") || role.getTitle().equals("ROLE_USER")) {
            throw new ServiceException(409, "Default role cannot be updated.");
        }
        roleRepository.deleteById(id);
    }
}
