package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;

public class MapperRoleEntity {

    public static From<RoleEntity, CreateRoleDTO> mapperRoleEntityFromCreateRoleDTO(final int applicationId) {
        return createRoleDTO -> RoleEntity.builder()
                .idApplication(applicationId)
                .name(createRoleDTO.getName())
                .description(createRoleDTO.getDescription())
                .build();
    }

    public static From<RoleDTO, RoleEntity> mapperRoleDTOFromRoleEntity() {
        return roleEntity -> RoleDTO.builder()
                .idApplication(roleEntity.getIdApplication())
                .name(roleEntity.getName())
                .description(roleEntity.getDescription())
                .build();
    }
}
