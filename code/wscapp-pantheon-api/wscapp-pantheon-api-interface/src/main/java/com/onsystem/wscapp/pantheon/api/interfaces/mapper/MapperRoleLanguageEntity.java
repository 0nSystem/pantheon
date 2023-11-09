package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageKeyEntity;

import java.util.function.Function;

public class MapperRoleLanguageEntity {


    public static Function<CreateRoleLanguageDTO, RoleLanguageEntity> mapperRoleLanguageEntityFromCreateRoleLanguage(final int roleId) {
        return createRoleLanguageDTO -> RoleLanguageEntity.builder()
                .roleLanguageKeyEntity(RoleLanguageKeyEntity.builder()
                        .idLanguage(createRoleLanguageDTO.getIdLanguage()).idRole(roleId).build())
                .name(createRoleLanguageDTO.getName())
                .description(createRoleLanguageDTO.getDescription())
                .build();
    }

    public static Function<RoleLanguageEntity, RoleLanguageDTO> mapperRoleLanguageDTOFromRoleLanguageEntity() {
        return roleLanguageEntity -> RoleLanguageDTO.builder()
                .idRole(roleLanguageEntity.getRoleLanguageKeyEntity().getIdRole())
                .idLanguage(roleLanguageEntity.getRoleLanguageKeyEntity().getIdLanguage())
                .name(roleLanguageEntity.getName())
                .description(roleLanguageEntity.getDescription())
                .build();
    }
}
