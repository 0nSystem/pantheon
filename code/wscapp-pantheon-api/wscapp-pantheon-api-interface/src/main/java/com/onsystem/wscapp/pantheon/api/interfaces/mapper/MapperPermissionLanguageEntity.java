package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageKeyEntity;

import java.util.function.Function;

public class MapperPermissionLanguageEntity {


    public static Function<CreatePermissionLanguageDTO, PermissionLanguageEntity> toEntity(final int permissionId) {
        return createPermissionLanguageDTO -> PermissionLanguageEntity.builder()
                .permissionLanguageKeyEntity(PermissionLanguageKeyEntity.builder().idPermission(permissionId).idLanguage(createPermissionLanguageDTO.getIdLanguage()).build())
                .name(createPermissionLanguageDTO.getName())
                .description(createPermissionLanguageDTO.getDescription())
                .build();
    }

    public static Function<PermissionLanguageEntity, PermissionLanguageDTO> toDto() {
        return permissionLanguageEntity -> PermissionLanguageDTO.builder()
                .idPermission(permissionLanguageEntity.getPermissionLanguageKeyEntity().getIdPermission())
                .idLanguage(permissionLanguageEntity.getPermissionLanguageKeyEntity().getIdLanguage())
                .name(permissionLanguageEntity.getName())
                .description(permissionLanguageEntity.getDescription())
                .build();
    }
}
