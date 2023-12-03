package com.onsystem.wscapp.pantheon.api.dto.applications.role;

import com.onsystem.wscapp.pantheon.api.dto.Constants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateRoleDTO {
    @Positive
    private int idRole;


    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 100, message = Constants.ErrorValidationMessages.MAX)
    private String name;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 255, message = Constants.ErrorValidationMessages.MAX)
    private String description;

}
