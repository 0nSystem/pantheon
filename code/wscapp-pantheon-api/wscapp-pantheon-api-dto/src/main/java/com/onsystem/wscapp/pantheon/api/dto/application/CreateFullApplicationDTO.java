package com.onsystem.wscapp.pantheon.api.dto.application;

import com.onsystem.wscapp.pantheon.api.dto.Constants;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeWithLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleWithLanguagesAndPermissionWithLanguagesDTO;
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
