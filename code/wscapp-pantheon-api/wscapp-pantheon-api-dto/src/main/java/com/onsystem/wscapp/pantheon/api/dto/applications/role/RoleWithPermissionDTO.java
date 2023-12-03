package com.onsystem.wscapp.pantheon.api.dto.applications.role;

import com.onsystem.wscapp.pantheon.api.dto.Constants;
import com.onsystem.wscapp.pantheon.api.dto.applications.permission.PermissionDTO;
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
public class RoleWithPermissionDTO {

    @NotNull(message = Constants.ErrorValidationMessages.NOT_NULL)
    private RoleDTO role;

    private Set<PermissionDTO> permissions;
}
