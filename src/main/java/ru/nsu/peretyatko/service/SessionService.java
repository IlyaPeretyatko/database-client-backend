package ru.nsu.peretyatko.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.peretyatko.error.exception.ServiceException;
import ru.nsu.peretyatko.model.Session;
import ru.nsu.peretyatko.model.User;
import ru.nsu.peretyatko.repository.SessionRepository;
import ru.nsu.peretyatko.dto.session.*;
import ru.nsu.peretyatko.mapper.SessionMapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionService {

    private final PasswordEncoder passwordEncoder;

    @Value("${session.expiration.duration}")
    private long sessionExpirationDuration;

    private final SessionRepository sessionRepository;

    private final UserService userService;

    private final SessionMapper sessionMapper;

    @Autowired
    public SessionService(SessionRepository sessionRepository, @Lazy UserService userService, SessionMapper sessionMapper, PasswordEncoder passwordEncoder) {
        this.sessionRepository = sessionRepository;
        this.userService = userService;
        this.sessionMapper = sessionMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SessionResponse createSession(SessionRequest sessionRequest) {
        User user = userService.findById(sessionRequest.getUserId());
        if (!passwordEncoder.matches(sessionRequest.getPassword(), user.getPassword())) {
            throw new ServiceException(401, "Password is wrong.");
        }
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(UUID.randomUUID(), user, now, now.plusMinutes(sessionExpirationDuration), true);
        sessionRepository.save(session);
        return sessionMapper.toSessionResponse(session);
    }

    @Transactional(readOnly = true)
    public SessionResponse getSessionById(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new ServiceException(404, "Session wasn't found."));
        return sessionMapper.toSessionResponse(session);
    }

    @Transactional
    public void closeSession(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new ServiceException(404, "Session wasn't found."));
        session.setActive(false);
        sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    protected Session findSessionById(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new ServiceException(404, "Session wasn't found."));
        if (!session.isActive()) throw new ServiceException(408, "Session is not active.");
        if (session.getExpirationTime().isBefore(LocalDateTime.now())) {
            closeSession(sessionId);
            throw new ServiceException(408, "Session timeout has expired.");
        }
        return session;
    }
}