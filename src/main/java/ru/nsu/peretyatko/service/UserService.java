package ru.nsu.peretyatko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.config.type.MailType;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.mapper.UserMapper;
import ru.nsu.peretyatko.model.user.Role;
import ru.nsu.peretyatko.model.user.User;
import ru.nsu.peretyatko.repository.UserRepository;
import ru.nsu.peretyatko.dto.user.*;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final MailService mailService;

    private final UserMapper userMapper;

    @Transactional
    public UserPostResponse createUser(UserPostRequest userPostRequest) {
        User user = userMapper.toUser(userPostRequest);
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
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