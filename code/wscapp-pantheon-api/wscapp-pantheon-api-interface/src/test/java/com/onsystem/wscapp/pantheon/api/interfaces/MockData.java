package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;


public class MockData {

    public static class DataMockSchemeApplicationDTO{
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

        public static RoleEntity.RoleEntityBuilder ROLE_MOCK = RoleEntity.builder()
                .name("name role entity")
                .description("description role entity");
    }
}
