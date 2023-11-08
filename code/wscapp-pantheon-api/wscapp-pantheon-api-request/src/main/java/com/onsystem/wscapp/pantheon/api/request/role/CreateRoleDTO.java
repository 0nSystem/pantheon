package com.onsystem.wscapp.pantheon.api.request.role;

import com.onsystem.wscapp.pantheon.api.request.Constants;
import com.onsystem.wscapp.pantheon.api.request.permission.CreatePermissionDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateRoleDTO {

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 100, message = Constants.ErrorValidationMessages.MAX)
    private String name;
    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 255, message = Constants.ErrorValidationMessages.MAX)
    private String description;

    private Set<CreateRoleLanguageDTO> roleLanguage;
    private Set<CreatePermissionDTO> rolePermission;
}
