package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.RoleDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperRoleEntity {

    @Mappings({
            @Mapping(source = "applicationId", target = "application.idApplication"),
            @Mapping(target = "idRole", ignore = true),
            @Mapping(target = "roleLanguages", ignore = true),
            @Mapping(target = "permissions", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract RoleEntity createToEntity(CreateRoleDTO createRoleDTO, final @NotNull Integer applicationId);

    @Mapping(target = "idApplication", source = "application.idApplication")
    public abstract RoleDTO toDto(RoleEntity roleEntity);

}
