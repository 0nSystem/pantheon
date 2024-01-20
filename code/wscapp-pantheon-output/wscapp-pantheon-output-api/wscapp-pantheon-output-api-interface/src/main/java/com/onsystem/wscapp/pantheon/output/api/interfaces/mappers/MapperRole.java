package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;

import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.RoleInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MapperRole {


    RoleInfoDTO toDto(RoleEntity roleEntity);


}
