package com.onsystem.wscapp.pantheon.api.request.user;

import com.onsystem.wscapp.pantheon.api.request.Constants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 50, message = Constants.ErrorValidationMessages.MAX)
    private String name;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 50, message = Constants.ErrorValidationMessages.MAX)
    private String surname;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 100, message = Constants.ErrorValidationMessages.MAX)
    private String email;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 30, message = Constants.ErrorValidationMessages.MAX)
    private String login;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 255, message = Constants.ErrorValidationMessages.MAX)
    private String password;
}
