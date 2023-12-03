package com.onsystem.wscapp.pantheon.api.dto.applications.application;

import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.AttributeWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.permission.PermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.role.RoleWithLanguagesAndPermissionWithLanguagesDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplicationFullInfoWithLanguagesDTO {

    @NotNull
    private ApplicationDTO application;

    private Set<ApplicationLanguageDTO> applicationLanguages;

    private Set<PermissionWithLanguagesDTO> applicationPermissions;

    private Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles;

    private Set<AttributeWithLanguagesDTO> applicationAttributes;

}
