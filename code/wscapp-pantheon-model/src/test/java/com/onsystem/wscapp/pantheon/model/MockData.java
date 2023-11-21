package com.onsystem.wscapp.pantheon.model;

import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;

import java.util.Set;

public class MockData {

    public static class DataMockSchemeApplication {
        public static final LanguageEntity language = LanguageEntity.builder()
                .name("es")
                .languageFamily("es")
                .iso6391Code("es")
                .build();

        public static CreateApplicationDTO createApplicationModel = CreateApplicationDTO.builder()
                .name("name")
                .description("description")
                .highIdUser(1)
                .build();
        public static CreateApplicationLanguageDTO.CreateApplicationLanguageDTOBuilder createApplicationLanguage = CreateApplicationLanguageDTO.builder()
                .name("name language")
                .description("description language");
    }
}
