package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleWithLanguagesAndPermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleWithLanguagesAndPermissionWithLanguagesDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Collection;
import java.util.Set;


public interface ICreateRoleService {
    Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles);
    @NotNull Set<RoleLanguageDTO> createRoleLanguages(final @Positive int idRole,
                                                      final @NotNull @NotEmpty Collection<CreateRoleLanguageDTO> createRoleLanguages);
}
