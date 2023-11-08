package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.api.request.application.CreateApplicationLanguageDTO;
import org.springframework.lang.Nullable;

import java.util.function.Function;

public class MapperApplicationLanguageEntity {

    public static Function<CreateApplicationLanguageDTO,ApplicationLanguageEntity> transformApplicationLanguageFromCreateApplicationLanguage(final @Nullable Integer applicationId) {
        return createApplicationLanguageDTO -> mapperApplicationLanguageFromCreateApplicationLanguage(applicationId).from(createApplicationLanguageDTO);
    }
    public static From<ApplicationLanguageEntity, CreateApplicationLanguageDTO> mapperApplicationLanguageFromCreateApplicationLanguage(final @Nullable Integer applicationId) {
        return createApplicationLanguage -> ApplicationLanguageEntity.builder()
                .name(createApplicationLanguage.getName())
                .description(createApplicationLanguage.getDescription())
                .applicationLanguageKeyEntity(ApplicationLanguageKeyEntity.builder()
                        .idApplication(applicationId)
                        .idLanguage(createApplicationLanguage.getIdLanguage())
                        .build())
                .build();
    }
}
