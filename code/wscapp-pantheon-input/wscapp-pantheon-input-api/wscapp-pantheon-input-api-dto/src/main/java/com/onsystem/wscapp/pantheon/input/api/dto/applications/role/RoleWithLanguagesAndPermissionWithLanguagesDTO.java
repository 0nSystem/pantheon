package com.onsystem.wscapp.pantheon.input.api.dto.applications.role;

import com.onsystem.wscapp.pantheon.input.api.dto.Constants;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.PermissionWithLanguagesDTO;
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
public class RoleWithLanguagesAndPermissionWithLanguagesDTO {
    @NotNull(message = Constants.ErrorValidationMessages.NOT_NULL)
    private RoleDTO role;

    private Set<RoleLanguageDTO> roleLanguages;

    private Set<PermissionWithLanguagesDTO> permissions;
}
