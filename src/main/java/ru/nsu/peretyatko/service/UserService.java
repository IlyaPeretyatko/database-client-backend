package ru.nsu.peretyatko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.config.type.MailType;
import ru.nsu.peretyatko.mapper.UserMapper;
import ru.nsu.peretyatko.model.User;
import ru.nsu.peretyatko.repository.UserRepository;
import ru.nsu.peretyatko.dto.user.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final MailService mailService;

    private final UserMapper userMapper;

    @Transactional
    public UserPostResponse createUser(UserPostRequest userPostRequest) {
        User user = userMapper.toUser(userPostRequest);
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

}