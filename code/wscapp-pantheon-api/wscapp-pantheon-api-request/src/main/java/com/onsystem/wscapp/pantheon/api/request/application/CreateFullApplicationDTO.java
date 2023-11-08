package com.onsystem.wscapp.pantheon.api.request.application;

import com.onsystem.wscapp.pantheon.api.request.Constants;
import com.onsystem.wscapp.pantheon.api.request.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.request.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.request.role.CreateRoleDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateFullApplicationDTO {

    @NotNull(message = Constants.ErrorValidationMessages.NOT_NULL)
    private CreateApplicationDTO application;

    private Set<CreateApplicationLanguageDTO> applicationLanguages;

    private Set<CreatePermissionDTO> applicationPermissions;

    private Set<CreateRoleDTO> applicationRoles;

    private Set<CreateAttributeDTO> applicationAttributes;


}
