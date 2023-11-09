package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;

import java.util.function.Function;

public class MapperPermissionEntity {

    public static Function<CreatePermissionWithLanguagesDTO, PermissionEntity> mapperPermissionEntityFromCreatePermissionWithLanguageDTO(final int idApplication) {
        return createPermissionWithLanguagesDTO -> PermissionEntity.builder()
                .name(createPermissionWithLanguagesDTO.getPermission().getName())
                .description(createPermissionWithLanguagesDTO.getPermission().getDescription())
                .idApplication(idApplication)
                .build();
    }

    public static Function<CreatePermissionDTO, PermissionEntity> mapperPermissionEntityFromCreatePermissionDTO(final int idApplication) {
        return createPermissionDTO -> PermissionEntity.builder()
                .name(createPermissionDTO.getName())
                .description(createPermissionDTO.getDescription())
                .idApplication(idApplication)
                .build();
    }

    public static Function<PermissionEntity, PermissionDTO> mapperPermissionDTOFromPermissionEntity() {
        return permissionEntity -> PermissionDTO.builder()
                .idPermission(permissionEntity.getIdPermission())
                .idApplication(permissionEntity.getIdApplication())
                .description(permissionEntity.getDescription())
                .name(permissionEntity.getName())
                .build();
    }
}
