package com.onsystem.wscapp.pantheon.api.dto.permission;

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
public class PermissionWithLanguagesDTO {

    @NotNull
    private PermissionDTO permission;

    private Set<PermissionLanguageDTO> permissionLanguages;
}
