package ru.nsu.peretyatko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.mapper.UserMapper;
import ru.nsu.peretyatko.model.User;
import ru.nsu.peretyatko.repository.UserRepository;
import ru.nsu.peretyatko.dto.user.*;
import ru.nsu.peretyatko.error.exception.*;


import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final SessionService sessionService;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy SessionService sessionService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserPostResponse createUser(UserPostRequest userPostRequest) {
        User user = userMapper.toUser(userPostRequest);
        User createdUser = userRepository.save(user);
        return userMapper.toUserPostResponse(createdUser);
    }

    @Transactional(readOnly = true)
    public UserGetResponse getUserById(long id, UUID sessionId) {
        sessionService.findSessionById(sessionId);
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(404, "User wasn't found."));
        return userMapper.toUserGetResponse(user);
    }

    @Transactional(readOnly = true)
    protected User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(404, "User wasn't found."));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}