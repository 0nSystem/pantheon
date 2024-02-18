package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;


import com.onsystem.wscapp.pantheon.output.api.dto.applications.PermissionInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.RoleInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.AttributeUserDataDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeUserDataProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserPermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserRoleInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface IMapperUser {


    UserInfoDTO toDto(UserInfoProjection userInfoProjection);


    PermissionInfoDTO permissionUserToDto(UserPermissionInfoProjection permissionInfoProjection);

    RoleInfoDTO roleUserToDto(UserRoleInfoProjection userRoleInfoProjection);

    default Set<AttributeUserDataDTO> attributeUserToDto(Collection<AttributeUserDataProjection> attributeUserDataProjections) {
        final Map<Integer, List<AttributeUserDataProjection>> attributeBelongIdAttribute = attributeUserDataProjections
                .stream()
                .collect(Collectors.groupingBy(AttributeUserDataProjection::getIdApplication));


        final Set<AttributeUserDataDTO> attributeGroupingValues = new HashSet<>();
        for (final Map.Entry<Integer, List<AttributeUserDataProjection>> attr : attributeBelongIdAttribute.entrySet()) {
            final AttributeUserDataProjection attrUserProjection = attr.getValue()
                    .stream().findFirst()
                    .orElseThrow();

            final AttributeUserDataDTO attrGroupValues = AttributeUserDataDTO.builder()
                    .attributeId(attrUserProjection.getIdAttribute())
                    .userId(attrUserProjection.getIdUser())
                    .name(attrUserProjection.getName())
                    .description(attrUserProjection.getDescription())
                    .values(attr.getValue().stream().map(AttributeUserDataProjection::getValue).toList())
                    .build();

            attributeGroupingValues.add(attrGroupValues);
        }

        return attributeGroupingValues;

    }

    ;
}
