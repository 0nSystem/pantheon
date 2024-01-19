package com.onsystem.wscapp.pantheon.input.api.dto.users;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAfterUserDTO {

    private Integer idUser;

    @NotEmpty
    @Max(50)
    private String name;

    @NotEmpty
    @Max(50)
    private String surname;

    @NotEmpty
    @Max(100)
    private String email;

    @NotEmpty
    @Max(30)
    private String login;

    @NotNull
    private Timestamp highDate;

    private Integer highIdUser;

}
