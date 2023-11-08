package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.request.application.CreateApplicationDTO;

public class MapperApplicationEntity {

    public static From<ApplicationEntity, CreateApplicationDTO> mapperApplicationEntityFromCreateApplication() {
        return createApplication -> ApplicationEntity.builder()
                .name(createApplication.getName())
                .description(createApplication.getDescription())
                .highIdUser(createApplication.getHighIdUser()).build();
    }

}
