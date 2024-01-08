package com.onsystem.wscapp.pantheon.api.dto.users;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @NotEmpty
    @Max(50)
    private String name;

    @NotEmpty
    @Max(50)
    private String surname;

    @NotEmpty
    @Max(100)
    @Email
    private String email;

    @NotEmpty
    @Max(30)
    private String login;

    @NotEmpty
    @Max(255)
    private String password;

    //FIXME remove createUserDTO
    @Positive
    @NotNull
    private Integer highIdUser;

}
