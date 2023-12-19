package com.onsystem.wscapp.pantheon.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class MapperUserAttributeEntity {


    public List<UserAttributeEntity> createToEntity(CreateUserAttributeDTO createUserAttributeDTO, int userId) {
        return createUserAttributeDTO.getValue().stream()
                .map(value ->
                        UserAttributeEntity
                                .builder()
                                .attribute(AttributeEntity.builder().idAttribute(createUserAttributeDTO.getAttributeId()).build())
                                .user(UserEntity.builder().idUser(userId).build())
                                .attribute_value(value)
                                .build()
                )
                .collect(Collectors.toList());
    }
}
