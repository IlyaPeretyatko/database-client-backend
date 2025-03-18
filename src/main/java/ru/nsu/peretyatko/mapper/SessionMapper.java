package ru.nsu.peretyatko.mapper;

import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.session.SessionResponse;
import ru.nsu.peretyatko.model.Session;

@Component
public class SessionMapper {
    public SessionResponse toSessionResponse(Session session) {
        SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setSessionId(session.getId());
        sessionResponse.setExpirationTime(session.getExpirationTime());
        sessionResponse.setActive(session.isActive());
        return sessionResponse;
    }
}
