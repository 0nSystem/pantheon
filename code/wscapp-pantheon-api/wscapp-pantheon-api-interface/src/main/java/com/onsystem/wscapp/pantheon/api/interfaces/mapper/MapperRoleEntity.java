package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;

import java.util.function.Function;

public class MapperRoleEntity {

    public static Function<CreateRoleDTO, RoleEntity> mapperRoleEntityFromCreateRoleDTO(final int applicationId) {
        return createRoleDTO -> RoleEntity.builder()
                .idApplication(applicationId)
                .name(createRoleDTO.getName())
                .description(createRoleDTO.getDescription())
                .build();
    }

    public static Function<RoleEntity, RoleDTO> mapperRoleDTOFromRoleEntity() {
        return roleEntity -> RoleDTO.builder()
                .idApplication(roleEntity.getIdApplication())
                .name(roleEntity.getName())
                .description(roleEntity.getDescription())
                .build();
    }
}
