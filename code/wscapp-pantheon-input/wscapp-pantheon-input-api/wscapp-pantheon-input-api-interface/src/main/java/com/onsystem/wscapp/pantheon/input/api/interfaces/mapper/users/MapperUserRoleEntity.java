package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users;

import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserRolesDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserRoleKeyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MapperUserRoleEntity {


    default List<UserRoleKeyEntity> deleteToEntity(DeleteUserRolesDTO deleteUserRolesDTO) {
        return deleteUserRolesDTO.getRoleIds().stream()
                .map(idRole -> UserRoleKeyEntity.builder()
                        .user(deleteUserRolesDTO.getUserId())
                        .role(idRole)
                        .build()
                ).collect(Collectors.toList());
    }

}
