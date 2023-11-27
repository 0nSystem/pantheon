package com.onsystem.wscapp.pantheon.api.interfaces.services.create;

import com.onsystem.wscapp.pantheon.api.dto.role.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Collection;
import java.util.Set;


public interface ICreateRoleService {

    @NotNull Set<RoleDTO> createRole(@Positive int idApplication, Set<CreateRoleDTO> createRole);
    @NotNull Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles);
    @NotNull Set<RoleLanguageDTO> createRoleLanguages(final @Positive int idRole,
                                                      final @NotNull @NotEmpty Collection<CreateRoleLanguageDTO> createRoleLanguages);
}
