package com.onsystem.wscapp.pantheon.api.request.role;

import com.onsystem.wscapp.pantheon.api.request.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateRoleRequest {

    @Positive
    @Min(value = 1, message = Constants.ErrorValidationMessages.MIN)
    private int idRole;
    //TODO
    @Positive
    @Min(value = 1, message = Constants.ErrorValidationMessages.MIN)
    private int idApplication;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 100, message = Constants.ErrorValidationMessages.SIZE)
    private String name;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 255, message = Constants.ErrorValidationMessages.SIZE)
    private String description;

}
