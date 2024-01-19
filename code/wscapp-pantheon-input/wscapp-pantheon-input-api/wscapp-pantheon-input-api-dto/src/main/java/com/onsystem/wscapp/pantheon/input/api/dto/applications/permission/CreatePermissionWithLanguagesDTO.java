package com.onsystem.wscapp.pantheon.input.api.dto.applications.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreatePermissionWithLanguagesDTO {
    private CreatePermissionDTO permission;
    private Set<CreatePermissionLanguageDTO> permissionLanguages;
}
