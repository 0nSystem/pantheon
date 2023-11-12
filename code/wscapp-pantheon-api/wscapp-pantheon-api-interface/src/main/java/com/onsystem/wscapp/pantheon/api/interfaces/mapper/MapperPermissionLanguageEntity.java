package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageKeyEntity;
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
public abstract class MapperPermissionLanguageEntity {

    @Mappings({
            @Mapping(source = "permissionId", target = "idPermission")
    })
    public abstract PermissionLanguageEntity toEntity(final CreatePermissionLanguageDTO createPermissionLanguageDTO,
                                                      final Integer permissionId);


    public abstract PermissionLanguageDTO toDto(final PermissionLanguageEntity permissionLanguageEntity);
}
