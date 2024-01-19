package com.onsystem.wscapp.pantheon.input.api.dto.applications.application;

import com.onsystem.wscapp.pantheon.input.api.dto.Constants;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.CreateAttributeWithLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.CreateRoleWithLanguagesAndPermissionWithLanguagesDTO;
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

    private Set<CreatePermissionWithLanguagesDTO> applicationPermissions;

    private Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles;

    private Set<CreateAttributeWithLanguageDTO> applicationAttributes;


}
