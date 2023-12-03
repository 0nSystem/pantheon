package com.onsystem.wscapp.pantheon.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionLanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperPermissionLanguageEntity {

    @Mappings({
            @Mapping(target = "language.idLanguage", source = "createPermissionLanguageDTO.idLanguage"),
            @Mapping(target = "permission.idPermission", source = "permissionId")
    })
    public abstract PermissionLanguageEntity createToEntity(final CreatePermissionLanguageDTO createPermissionLanguageDTO,
                                                            final Integer permissionId);

    @Mappings({
            @Mapping(target = "idLanguage", source = "language.idLanguage"),
            @Mapping(target = "idPermission", source = "permission.idPermission")
    })
    public abstract PermissionLanguageDTO toDto(final PermissionLanguageEntity permissionLanguageEntity);
}
