package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import org.springframework.lang.Nullable;

import java.util.function.Function;

public class MapperApplicationLanguageEntity {

    public static Function<CreateApplicationLanguageDTO, ApplicationLanguageEntity> mapperApplicationLanguageFromCreateApplicationLanguage(final @Nullable Integer applicationId) {
        return createApplicationLanguage -> ApplicationLanguageEntity.builder()
                .name(createApplicationLanguage.getName())
                .description(createApplicationLanguage.getDescription())
                .applicationLanguageKeyEntity(ApplicationLanguageKeyEntity.builder()
                        .idApplication(applicationId)
                        .idLanguage(createApplicationLanguage.getIdLanguage())
                        .build())
                .build();
    }


    public static Function<ApplicationLanguageEntity, ApplicationLanguageDTO> mapperApplicationLanguageDTOFromApplicationEntity() {
        return entity -> ApplicationLanguageDTO.builder()
                .idApplication(entity.getApplicationLanguageKeyEntity().getIdApplication())
                .idLanguage(entity.getApplicationLanguageKeyEntity().getIdLanguage())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

}
