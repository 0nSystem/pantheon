package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;


import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.ISessionManager;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class MapperApplicationEntity {

    @Autowired
    ITimeHelper ITimeHelper;
    @Autowired
    ISessionManager iSessionManager;

    @Mappings({
            @Mapping(target = "idApplication", ignore = true),
            @Mapping(target = "deleteIdUser", ignore = true),
            @Mapping(target = "deleteDate", ignore = true),
            @Mapping(target = "highIdUser", ignore = true),
            @Mapping(target = "highDate", expression = "java(ITimeHelper.now())"),
            @Mapping(target = "applicationLanguages", ignore = true),
            @Mapping(target = "permissions", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "attributes", ignore = true)
    })
    public abstract ApplicationEntity createToEntity(CreateApplicationDTO createApplicationDTO);

    @AfterMapping
    void handlerOptionalHighIdUser(@MappingTarget ApplicationEntity applicationEntity,  CreateApplicationDTO createApplication) {
        // Maneja el campo Optional aquí según tus necesidades
        createApplication.getHighIdUser().ifPresentOrElse(
                applicationEntity::setHighIdUser,
                () -> applicationEntity.setHighIdUser(iSessionManager.currentIdUser()));
    }

    public abstract ApplicationDTO entityToDTO(ApplicationEntity applicationEntity);


}
