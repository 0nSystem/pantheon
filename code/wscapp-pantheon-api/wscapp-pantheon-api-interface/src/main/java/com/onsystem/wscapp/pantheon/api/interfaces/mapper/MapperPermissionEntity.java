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
            @Mapping(source = "applicationId", target = "idApplication"),
            @Mapping(target = "idPermission", ignore = true)
    })
    public abstract PermissionEntity toEntity(final CreatePermissionDTO createPermissionDTO,
                                              final @NotNull Integer applicationId);

    public abstract PermissionDTO toDto(final PermissionEntity permissionEntity);
}
