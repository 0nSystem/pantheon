package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.dto.permission.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Set;

public interface ICreatePermissionService {
    @NotNull Set<PermissionWithLanguagesDTO> createPermissionWithLanguages(final int applicationId,
                                                                           final @Nullable Integer idRole,
                                                                           final @NotNull @NotEmpty Collection<CreatePermissionWithLanguagesDTO> createPermissionWithLanguages);
    @NotNull Set<PermissionDTO> createPermission(final int applicationId, final Collection<CreatePermissionDTO> createPermission);

    @NotNull Set<PermissionLanguageDTO> createPermissionLanguages(final @Positive int permissionId,
                                                                  final @NotNull @NotEmpty Collection<CreatePermissionLanguageDTO> createPermissionLanguages);

    void createRelationsRoleWithPermission(final @Positive int idRole,
                                           final @NotNull @NotEmpty Collection<Integer> idsPermissionsInserted);
}
