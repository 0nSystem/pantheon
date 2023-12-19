package com.onsystem.wscapp.pantheon.api.dto.users;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    private String email;

    @NotEmpty
    @Max(30)
    private String login;

    @NotEmpty
    @Max(255)
    private String password;

    @Positive
    @NotNull
    private Integer highIdUser;

}
