package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.helpers.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;


public class MapperApplicationEntity {

    @Autowired
    private TimeHelper timeHelper;

    public static Function<CreateApplicationDTO, ApplicationEntity> fnToEntity() {
        return createApplication -> ApplicationEntity.builder()
                .name(createApplication.getName())
                .description(createApplication.getDescription())
                .highIdUser(createApplication.getHighIdUser())
                .build();
    }

    public static Function<ApplicationEntity, ApplicationDTO> fnToDto() {
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
