package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.dto.application.*;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.*;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleWithLanguagesAndPermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleWithLanguagesAndPermissionWithLanguagesDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Set;

/**
 * This service used to create entities reference by schema applications
 */
public interface ICreateSchemaApplicationDependencies {

    @NotNull ApplicationFullInfoWithLanguagesDTO createFullApplication(final @NotNull CreateFullApplicationDTO application);

    @NotNull ApplicationDTO createApplication(final @NotNull CreateApplicationDTO createApplication);

    @NotNull Set<ApplicationLanguageDTO> createApplicationLanguages(
            final @Positive int applicationId,
            final @NotNull @NotEmpty Collection<CreateApplicationLanguageDTO> createApplicationLanguage);

    @NotNull Set<PermissionWithLanguagesDTO> createPermissionWithLanguages(final int applicationId,
                                                                           final @Nullable Integer idRole,
                                                                           final @NotNull @NotEmpty Collection<CreatePermissionWithLanguagesDTO> createPermissionWithLanguages);

    @NotNull Set<PermissionDTO> createPermission(final int applicationId, final Collection<CreatePermissionDTO> createPermission);

    @NotNull Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            @NotNull @NotEmpty Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles);

    @NotNull Set<PermissionLanguageDTO> createPermissionLanguages(final @Positive int permissionId,
                                                                  final @NotNull @NotEmpty Collection<CreatePermissionLanguageDTO> createPermissionLanguages);

    @NotNull Set<RoleLanguageDTO> createRoleLanguages(final @Positive int idRole,
                                                      final @NotNull @NotEmpty Collection<CreateRoleLanguageDTO> createRoleLanguages);

    void createRelationsRoleWithPermission(final @Positive int idRole,
                                           final @NotNull @NotEmpty Collection<Integer> idsPermissionsInserted);

    @NotNull Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final @Positive int applicationId,
                                                                          final @NotNull @NotEmpty Collection<CreateAttributeDTO> createAttribute);

    @NotNull Set<AttributeLanguageDTO> createAttributesLanguages(final int attributeId,
                                                                 final @NotNull @NotEmpty Collection<CreateAttributeLanguageDTO> createAttribute);

}
