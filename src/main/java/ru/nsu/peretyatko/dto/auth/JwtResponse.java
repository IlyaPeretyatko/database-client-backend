package ru.nsu.peretyatko.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
