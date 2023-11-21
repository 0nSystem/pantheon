package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.helpers.ITimeHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class MapperApplicationEntity {

    @Autowired
    ITimeHelper ITimeHelper;

    @Mappings({
            @Mapping(target = "idApplication", ignore = true),
            @Mapping(target = "deleteIdUser", ignore = true),
            @Mapping(target = "deleteDate", ignore = true),
            @Mapping(target = "highDate", expression = "java(ITimeHelper.now())"),
            @Mapping(target = "applicationLanguages", ignore = true),
            @Mapping(target = "permissions", ignore = true),
            @Mapping(target = "roles", ignore = true)
    })
    public abstract ApplicationEntity createEntityToEntity(CreateApplicationDTO createApplicationDTO);


    public abstract ApplicationDTO entityToDTO(ApplicationEntity applicationEntity);


}
