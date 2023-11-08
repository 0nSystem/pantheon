package com.onsystem.wscapp.pantheon.api.request.role;

import com.onsystem.wscapp.pantheon.api.request.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateRoleLanguageDTO {

    //TODO
    @Positive
    private Integer idLanguage;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 100, message = Constants.ErrorValidationMessages.MAX)
    private String name;
    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 255, message = Constants.ErrorValidationMessages.MAX)
    private String description;
}
