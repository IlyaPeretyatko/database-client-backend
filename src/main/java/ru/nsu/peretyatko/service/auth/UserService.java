package ru.nsu.peretyatko.service.auth;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nsu.peretyatko.config.type.MailType;
import ru.nsu.peretyatko.dto.auth.user.UserPatchRequest;
import ru.nsu.peretyatko.dto.auth.user.UserPostRequest;
import ru.nsu.peretyatko.dto.auth.user.UserPostResponse;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.auth.UserMapper;
import ru.nsu.peretyatko.model.auth.Role;
import ru.nsu.peretyatko.model.auth.User;
import ru.nsu.peretyatko.repository.auth.RoleRepository;
import ru.nsu.peretyatko.repository.auth.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MailService mailService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, MailService mailService,
                       UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserPostResponse createUser(UserPostRequest userPostRequest) {
        User user = userMapper.toUser(userPostRequest);
        Optional<Role> userRole = roleRepository.findByTitle("ROLE_USER");
        if (userRole.isPresent()) {
            Set<Role> roles = Set.of(userRole.get());
            user.setRoles(roles);
        }
        User createdUser = userRepository.save(user);
        mailService.sendEmail(user, MailType.REGISTER);
        return userMapper.toUserPostResponse(createdUser);
    }

    @Transactional
    public boolean verifyEmail(String token) {
        User user = userRepository.findByVerificationCode(token);
        if (user != null) {
            user.setEmailConfirmed(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void requestResetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ServiceException(404, "User wasn't found."));
        if (!user.isEmailConfirmed()) throw new ServiceException(403, "Email not confirmed.");
        user.setVerificationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        mailService.sendEmail(user, MailType.RESET_PASSWORD);
    }

    @Transactional
    public void resetPassword(String token, UserPatchRequest userPatchRequest) {
        User user = userRepository.findByVerificationCode(token);
        if (user != null) {
            user.setVerificationCode(null);
            user.setPassword(passwordEncoder.encode(userPatchRequest.getPassword()));
            userRepository.save(user);
        }
    }

    @Transactional
    public void addRoleForUser(@PathVariable long id, @RequestBody String title) {
        Role newRole = roleRepository.findByTitle(title).orElseThrow(() -> new ServiceException(404, "Role wasn't found."));
        User user = getUserById(id);
        Set<Role> roles = user.getRoles();
        roles.add(newRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRoleForUser(@PathVariable long id, @RequestBody String title) {
        Role removeRole = roleRepository.findByTitle(title).orElseThrow(() -> new ServiceException(404, "Role wasn't found."));
        User user = getUserById(id);
        Set<Role> roles = user.getRoles();
        roles.remove(removeRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String email) {
        return userRepository.existsByName(email);
    }

    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new ServiceException(404, "User wasn't found."));
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(404, "User wasn't found."));
    }

}