package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import jakarta.validation.constraints.NotNull;
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
public abstract class MapperPermissionEntity {


    @Mappings({

            @Mapping(target = "application.idApplication", source = "applicationId"),
            @Mapping(target = "idPermission", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "permissionLanguages", ignore = true)
    })
    public abstract PermissionEntity toEntity(final CreatePermissionDTO createPermissionDTO,
                                              final @NotNull Integer applicationId);

    @Mappings({
            @Mapping(target = "idApplication", source = "application.idApplication")
    })
    public abstract PermissionDTO toDto(final PermissionEntity permissionEntity);
}
