package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperRoleLanguageEntity {


    @Mappings({
            @Mapping(target = "language.idLanguage", source = "createRoleLanguageDTO.idLanguage"),
            @Mapping(target = "role.idRole", source = "roleId")
    })
    public abstract RoleLanguageEntity createToEntity(CreateRoleLanguageDTO createRoleLanguageDTO, final Integer roleId);

    @Mappings({
            @Mapping(target = "idRole", source = "role.idRole"),
            @Mapping(target = "idLanguage", source = "language.idLanguage")
    })
    public abstract RoleLanguageDTO toDto(RoleLanguageEntity roleLanguageEntity);

}
