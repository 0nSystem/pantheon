package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.*;
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
