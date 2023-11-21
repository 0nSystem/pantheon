package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;


public class MockData {

    public static class DataMockSchemeApplication {

        public static final LanguageEntity LANGUAGE_MOCK = LanguageEntity.builder()
                .name("es")
                .languageFamily("es")
                .iso6391Code("es")
                .build();

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
    }
}
