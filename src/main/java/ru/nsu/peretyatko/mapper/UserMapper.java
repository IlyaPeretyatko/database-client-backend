package ru.nsu.peretyatko.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nsu.peretyatko.dto.user.UserGetResponse;
import ru.nsu.peretyatko.dto.user.UserPostRequest;
import ru.nsu.peretyatko.dto.user.UserPostResponse;
import ru.nsu.peretyatko.model.User;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(UserPostRequest userPostRequest) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userPostRequest.getPassword()));
        user.setName(userPostRequest.getName());
        user.setEmail(userPostRequest.getEmail());
        user.setEmailConfirmed(false);
        user.setVerificationCode(UUID.randomUUID().toString());
        return user;
    }

    public UserPostResponse toUserPostResponse(User user) {
        UserPostResponse userPostResponse = new UserPostResponse();
        userPostResponse.setId(user.getId());
        return userPostResponse;
    }

    public UserGetResponse toUserGetResponse(User user) {
        UserGetResponse userGetResponse = new UserGetResponse();
        userGetResponse.setId(user.getId());
        userGetResponse.setName(user.getName());
        userGetResponse.setEmail(user.getEmail());
        return userGetResponse;
    }


}
