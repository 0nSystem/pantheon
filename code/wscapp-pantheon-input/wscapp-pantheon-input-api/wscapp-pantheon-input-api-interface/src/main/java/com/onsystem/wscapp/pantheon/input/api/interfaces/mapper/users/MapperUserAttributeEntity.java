package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class MapperUserAttributeEntity {


    public List<UserAttributeEntity> createToEntity(CreateUserAttributeDTO createUserAttributeDTO) {
        return createUserAttributeDTO.getValue().stream()
                .map(value ->
                        UserAttributeEntity
                                .builder()
                                .attribute(AttributeEntity.builder().idAttribute(createUserAttributeDTO.getAttributeId()).build())
                                .user(UserEntity.builder().idUser(createUserAttributeDTO.getUserId()).build())
                                .attribute_value(value)
                                .build()
                )
                .collect(Collectors.toList());
    }

}
