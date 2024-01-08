package com.onsystem.wscapp.pantheon.api.interfaces.mapper.users;

import com.onsystem.wscapp.pantheon.api.dto.users.DeleteUserRolesDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleKeyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
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
