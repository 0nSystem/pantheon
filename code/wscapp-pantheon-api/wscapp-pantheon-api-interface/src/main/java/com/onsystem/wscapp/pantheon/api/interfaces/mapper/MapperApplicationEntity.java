package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;

public class MapperApplicationEntity {

    public static From<ApplicationEntity, CreateApplicationDTO> mapperApplicationEntityFromCreateApplication() {
        return createApplication -> ApplicationEntity.builder()
                .name(createApplication.getName())
                .description(createApplication.getDescription())
                .highIdUser(createApplication.getHighIdUser()).build();
    }

    public static From<ApplicationDTO,ApplicationEntity> mapperApplicationDTOFromApplicationEntity() {
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
