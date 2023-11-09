package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;

import java.util.function.Function;

public class MapperApplicationEntity {

    public static Function<CreateApplicationDTO, ApplicationEntity> mapperApplicationEntityFromCreateApplication() {
        return createApplication -> ApplicationEntity.builder()
                .name(createApplication.getName())
                .description(createApplication.getDescription())
                .highIdUser(createApplication.getHighIdUser()).build();
    }

    public static Function<ApplicationEntity, ApplicationDTO> mapperApplicationDTOFromApplicationEntity() {
        return appEntity -> ApplicationDTO.builder()
                .idApplication(appEntity.getIdApplication())
                .name(appEntity.getName())
                .description(appEntity.getDescription())
                .highDate(appEntity.getHighDate())
                .highIdUser(appEntity.getHighIdUser())
                .deleteDate(appEntity.getDeleteDate())
                .deleteIdUser(appEntity.getDeleteIdUser())
                .build();
    }


}
