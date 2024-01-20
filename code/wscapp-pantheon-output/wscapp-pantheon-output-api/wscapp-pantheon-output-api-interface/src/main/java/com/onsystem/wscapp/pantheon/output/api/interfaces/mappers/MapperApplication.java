package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;


import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MapperApplication {


    ApplicationInfoDTO toDto(ApplicationEntity applicationEntity);
}
