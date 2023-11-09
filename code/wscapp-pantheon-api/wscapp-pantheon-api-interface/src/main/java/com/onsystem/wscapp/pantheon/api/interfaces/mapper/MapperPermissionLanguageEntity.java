package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageKeyEntity;

public class MapperPermissionLanguageEntity {


    public static From<PermissionLanguageEntity, CreatePermissionLanguageDTO> mapperPermissionLanguageEntityFromCreatePermissionLanguageDTO(final int permissionId) {
        return createPermissionLanguageDTO -> PermissionLanguageEntity.builder()
                .permissionLanguageKeyEntity(PermissionLanguageKeyEntity.builder().idPermission(permissionId).idLanguage(createPermissionLanguageDTO.getIdLanguage()).build())
                .name(createPermissionLanguageDTO.getName())
                .description(createPermissionLanguageDTO.getDescription())
                .build();
    }

    public static From<PermissionLanguageDTO, PermissionLanguageEntity> mapperPermissionLanguageDTOFromLanguageEntity() {
        return permissionLanguageEntity -> PermissionLanguageDTO.builder()
                .idPermission(permissionLanguageEntity.getPermissionLanguageKeyEntity().getIdPermission())
                .idLanguage(permissionLanguageEntity.getPermissionLanguageKeyEntity().getIdLanguage())
                .name(permissionLanguageEntity.getName())
                .description(permissionLanguageEntity.getDescription())
                .build();
    }
}
