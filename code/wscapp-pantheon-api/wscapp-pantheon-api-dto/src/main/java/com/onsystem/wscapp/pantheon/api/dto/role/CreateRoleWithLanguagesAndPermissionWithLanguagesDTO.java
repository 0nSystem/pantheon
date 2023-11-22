package com.onsystem.wscapp.pantheon.api.dto.role;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
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
public class CreateRoleWithLanguagesAndPermissionWithLanguagesDTO {

    @NotNull
    private CreateRoleDTO role;

    private Set<CreateRoleLanguageDTO> roleLanguage;
    private Set<CreatePermissionWithLanguagesDTO> rolePermission;
}
