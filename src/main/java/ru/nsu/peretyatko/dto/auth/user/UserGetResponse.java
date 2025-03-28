package ru.nsu.peretyatko.dto.auth.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetResponse {

    private long id;

    private String name;

    private String email;

}
