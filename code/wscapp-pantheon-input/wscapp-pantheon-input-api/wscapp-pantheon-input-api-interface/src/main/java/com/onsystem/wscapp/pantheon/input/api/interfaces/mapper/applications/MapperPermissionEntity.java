package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperPermissionEntity {


    @Mappings({

            @Mapping(target = "application.idApplication", source = "applicationId"),
            @Mapping(target = "idPermission", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "permissionLanguages", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract PermissionEntity createToEntity(final CreatePermissionDTO createPermissionDTO,
                                                    final @NotNull Integer applicationId);

    @Mappings({
            @Mapping(target = "idApplication", source = "application.idApplication")
    })
    public abstract PermissionDTO toDto(final PermissionEntity permissionEntity);
}
