package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperRoleEntity {

    @Mappings({
            @Mapping(source = "applicationId", target = "idApplication"),
            @Mapping(target = "idRole", ignore = true)
    })
    public abstract RoleEntity toEntity(CreateRoleDTO createRoleDTO, final @NotNull Integer applicationId);

    public abstract RoleDTO toDto(RoleEntity roleEntity);

}
