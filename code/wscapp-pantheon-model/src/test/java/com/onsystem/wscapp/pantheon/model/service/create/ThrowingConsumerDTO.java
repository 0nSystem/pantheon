package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
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

}
