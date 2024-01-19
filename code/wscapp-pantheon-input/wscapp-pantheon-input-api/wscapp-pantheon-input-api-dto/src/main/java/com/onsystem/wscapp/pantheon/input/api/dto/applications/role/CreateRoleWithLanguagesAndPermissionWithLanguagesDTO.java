package com.onsystem.wscapp.pantheon.input.api.dto.applications.role;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.CreatePermissionWithLanguagesDTO;
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

    private Set<CreateRoleLanguageDTO> roleLanguages;
    private Set<CreatePermissionWithLanguagesDTO> rolePermission;
}
