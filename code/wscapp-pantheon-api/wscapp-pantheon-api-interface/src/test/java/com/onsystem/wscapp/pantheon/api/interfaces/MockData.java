package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;


public class MockData {

    public static class DataCreateMockSchemeApplicationDTO {
        public static CreateApplicationDTO CREATE_APPLICATION_MOCK = CreateApplicationDTO.builder()
                .name("name")
                .description("description")
                .highIdUser(1)
                .build();
        public static CreateApplicationLanguageDTO.CreateApplicationLanguageDTOBuilder CREATE_APPLICATION_LANGUAGE_MOCK = CreateApplicationLanguageDTO.builder()
                .name("name language")
                .description("description language");
        public static CreatePermissionDTO CREATE_PERMISSION_MOCK = CreatePermissionDTO.builder()
                .name("permission name")
                .description("permission description")
                .build();

        public static CreatePermissionLanguageDTO.CreatePermissionLanguageDTOBuilder CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER = CreatePermissionLanguageDTO.builder()
                .name("permission name")
                .description("permission description");

        public static CreateRoleDTO CREATE_ROLE_MOCK = CreateRoleDTO.builder()
                .name("role name")
                .description("role description")
                .build();

        public static CreateRoleLanguageDTO.CreateRoleLanguageDTOBuilder CREATE_ROLE_LANGUAGE_MOCK = CreateRoleLanguageDTO.builder()
                .name("role name")
                .description("role description");


        public static CreateAttributeDTO CREATE_ATTRIBUTE_MOCK = CreateAttributeDTO.builder()
                .name("name attribute")
                .description("description attribute")
                .build();

        public static CreateAttributeLanguageDTO.CreateAttributeLanguageDTOBuilder CREATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER = CreateAttributeLanguageDTO.builder()
                .name("name attribute")
                .description("description attribute");

    }

    public static class DataUpdateMockSchemeApplicationDTO {
        public static UpdateApplicationDTO.UpdateApplicationDTOBuilder UPDATE_APPLICATION_MOCK_BUILDER = UpdateApplicationDTO.builder()
                .name("update name")
                .description("update description");

        public static UpdateApplicationLanguageDTO.UpdateApplicationLanguageDTOBuilder UPDATE_APPLICATION_LANGUAGE_MOCK_BUILDER = UpdateApplicationLanguageDTO.builder()
                .name("update name")
                .description("update description");

        public static UpdateRoleDTO.UpdateRoleDTOBuilder UPDATE_ROLE_MOCK_BUILDER = UpdateRoleDTO.builder()
                .name("update name")
                .description("update description");
        public static UpdateRoleLanguageDTO.UpdateRoleLanguageDTOBuilder UPDATE_ROLE_LANGUAGE_MOCK_BUILDER = UpdateRoleLanguageDTO.builder()
                .name("update name")
                .description("update description");

        public static UpdatePermissionDTO.UpdatePermissionDTOBuilder UPDATE_PERMISSION_MOCK_BUILDER = UpdatePermissionDTO.builder()
                .name("update name")
                .description("update description");
        public static UpdatePermissionLanguageDTO.UpdatePermissionLanguageDTOBuilder UPDATE_PERMISSION_LANGUAGE_MOCK_BUILDER = UpdatePermissionLanguageDTO.builder()
                .name("update name")
                .description("update description");

        public static UpdateAttributeDTO.UpdateAttributeDTOBuilder UPDATE_ATTRIBUTE_MOCK_BUILDER = UpdateAttributeDTO.builder()
                .name("update name")
                .description("update description");

        public static UpdateAttributeLanguageDTO.UpdateAttributeLanguageDTOBuilder UPDATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER = UpdateAttributeLanguageDTO.builder()
                .name("update name")
                .description("update description");
    }

    public static class DataMockSchemeApplicationEntities {

        public static final LanguageEntity LANGUAGE_MOCK = LanguageEntity.builder()
                .name("es")
                .languageFamily("es")
                .iso6391Code("es")
                .build();

        public static ApplicationEntity APPLICATION_MOCK = ApplicationEntity.builder()
                .name("name")
                .description("description")
                .highIdUser(1)
                .build();

        public static PermissionEntity.PermissionEntityBuilder PERMISSION_MOCK_BUILDER = PermissionEntity.builder()
                .name("name permission entity")
                .description("description permission entity");

        public static RoleEntity.RoleEntityBuilder ROLE_MOCK_BUILDER = RoleEntity.builder()
                .name("name role entity")
                .description("description role entity");

        public static AttributeEntity.AttributeEntityBuilder ATTRIBUTE_MOCK_BUILDER = AttributeEntity.builder()
                .name("name attribute")
                .description("description attribute");
    }
}
