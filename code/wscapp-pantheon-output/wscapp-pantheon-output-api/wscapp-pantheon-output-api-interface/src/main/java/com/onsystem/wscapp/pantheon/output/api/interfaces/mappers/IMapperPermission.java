package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.PermissionInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.PermissionInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface IMapperPermission {



    PermissionInfoDTO toDto(PermissionInfoProjection permissionInfoProjection);

}
