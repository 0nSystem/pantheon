package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;

public class MapperPermissionEntity {

    public static From<PermissionEntity, CreatePermissionWithLanguagesDTO> mapperPermissionEntityFromCreatePermissionWithLanguageDTO(final int idApplication) {
        return createPermissionWithLanguagesDTO -> PermissionEntity.builder()
                .name(createPermissionWithLanguagesDTO.getPermission().getName())
                .description(createPermissionWithLanguagesDTO.getPermission().getDescription())
                .idApplication(idApplication)
                .build();
    }

    public static From<PermissionEntity, CreatePermissionDTO> mapperPermissionEntityFromCreatePermissionDTO(final int idApplication) {
        return createPermissionDTO -> PermissionEntity.builder()
                .name(createPermissionDTO.getName())
                .description(createPermissionDTO.getDescription())
                .idApplication(idApplication)
                .build();
    }

    public static From<PermissionDTO, PermissionEntity> mapperPermissionDTOFromPermissionEntity() {
        return permissionEntity -> PermissionDTO.builder()
                .idPermission(permissionEntity.getIdPermission())
                .idApplication(permissionEntity.getIdApplication())
                .description(permissionEntity.getDescription())
                .name(permissionEntity.getName())
                .build();
    }
}
