package com.onsystem.wscapp.pantheon.output.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    private Integer idUser;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;

    private Timestamp highDate;
    private Integer highIdUser;
}
