package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class ThrowingConsumerDTO {

    public static ThrowingConsumer<PermissionDTO> caseDefaultCorrectCreatePermissionDTO(final int applicationId) {
        return (permission) -> {
            Assertions.assertNotNull(permission);
            Assertions.assertTrue(permission.getIdPermission() > 0);
            Assertions.assertEquals(applicationId, permission.getIdApplication());
            Assertions.assertNotNull(permission.getName());
            Assertions.assertNotNull(permission.getDescription());
        };
    }

    public static ThrowingConsumer<PermissionLanguageDTO> caseDefaultCorrectCreatePermissionLanguageDTO(final int permissionId, final int languageId) {
        return (permission) -> {
            Assertions.assertNotNull(permission);
            Assertions.assertEquals(permissionId, permission.getIdPermission());
            Assertions.assertEquals(languageId, permission.getIdLanguage());
            Assertions.assertNotNull(permission.getName());
            Assertions.assertNotNull(permission.getDescription());
        };
    }


    public static ThrowingConsumer<AttributeDTO> caseDefaultCorrectCreateAttribute(final int applicationId) {
        return attribute -> {
            Assertions.assertNotNull(attribute);
            Assertions.assertTrue(attribute.getIdApplication() > 0);
            Assertions.assertEquals(applicationId, attribute.getIdApplication());
            Assertions.assertNotNull(attribute.getName());
            Assertions.assertNotNull(attribute.getDescription());
        };
    }

    public static ThrowingConsumer<AttributeLanguageDTO> caseDefaultCorrectCreateAttributeLanguage(
            final int attributeId, final int languageId) {
        return attributeLanguage -> {
            Assertions.assertNotNull(attributeLanguage);
            Assertions.assertEquals(attributeId, attributeLanguage.getIdAttribute());
            Assertions.assertEquals(languageId, attributeLanguage.getIdLanguage());
            Assertions.assertNotNull(attributeLanguage.getName());
            Assertions.assertNotNull(attributeLanguage.getDescription());
        };
    }

    public static ThrowingConsumer<RoleDTO> caseDefaultCorrectCreateRole(final int applicationId) {
        return roleDTO -> {
            Assertions.assertNotNull(roleDTO);
            Assertions.assertTrue(roleDTO.getIdRole() > 0);
            Assertions.assertEquals(applicationId, roleDTO.getIdApplication());
            Assertions.assertNotNull(roleDTO.getName());
            Assertions.assertNotNull(roleDTO.getDescription());
        };
    }

    public static ThrowingConsumer<RoleLanguageDTO> caseDefaultCorrectCreateRoleLanguage(
            final int roleId, final int languageId
    ) {
        return roleLanguageDTO -> {
            Assertions.assertNotNull(roleLanguageDTO);
            Assertions.assertEquals(roleId, roleLanguageDTO.getIdRole());
            Assertions.assertEquals(languageId, roleLanguageDTO.getIdLanguage());
            Assertions.assertNotNull(roleLanguageDTO.getName());
            Assertions.assertNotNull(roleLanguageDTO.getDescription());
        };
    }

    public static ThrowingConsumer<ApplicationEntity> caseDefaultCorrectUpdateApplication(
            final @NotNull UpdateApplicationDTO updateApplication
    ) {
        return applicationEntity -> {
            Assertions.assertNotNull(applicationEntity);
            Assertions.assertEquals(updateApplication.getIdApplication(), applicationEntity.getIdApplication());
            Assertions.assertEquals(updateApplication.getName(), applicationEntity.getName());
            Assertions.assertEquals(updateApplication.getDescription(), applicationEntity.getDescription());
        };
    }

    public static ThrowingConsumer<ApplicationLanguageEntity> caseDefaultCorrectUpdateApplicationLanguage(
            final @NotNull UpdateApplicationLanguageDTO updateApplicationLanguage
    ) {
        return applicationEntity -> {
            Assertions.assertNotNull(applicationEntity);
            Assertions.assertEquals(updateApplicationLanguage.getIdApplication(), applicationEntity.getApplication().getIdApplication());
            Assertions.assertEquals(updateApplicationLanguage.getIdLanguage(), applicationEntity.getLanguage().getIdLanguage());
            Assertions.assertEquals(updateApplicationLanguage.getName(), applicationEntity.getName());
            Assertions.assertEquals(updateApplicationLanguage.getDescription(), applicationEntity.getDescription());
        };
    }

    public static ThrowingConsumer<RoleEntity> caseDefaultCorrectUpdateRole(
            final @NotNull UpdateRoleDTO updateRoleDTO
    ) {
        return roleEntity -> {
            Assertions.assertNotNull(roleEntity);
            Assertions.assertEquals(updateRoleDTO.getIdRole(), roleEntity.getIdRole());
            Assertions.assertEquals(updateRoleDTO.getName(), roleEntity.getName());
            Assertions.assertEquals(updateRoleDTO.getDescription(), roleEntity.getDescription());
        };
    }

    public static ThrowingConsumer<RoleLanguageEntity> caseDefaultCorrectUpdateRoleLanguage(
            final @NotNull UpdateRoleLanguageDTO updateRoleLanguageDTO
    ) {
        return roleLanguageEntity -> {
            Assertions.assertNotNull(roleLanguageEntity);
            Assertions.assertEquals(updateRoleLanguageDTO.getIdLanguage(), roleLanguageEntity.getLanguage().getIdLanguage());
            Assertions.assertEquals(updateRoleLanguageDTO.getIdRole(), roleLanguageEntity.getRole().getIdRole());
            Assertions.assertEquals(updateRoleLanguageDTO.getName(), roleLanguageEntity.getName());
            Assertions.assertEquals(updateRoleLanguageDTO.getDescription(), roleLanguageEntity.getDescription());
        };
    }

}
