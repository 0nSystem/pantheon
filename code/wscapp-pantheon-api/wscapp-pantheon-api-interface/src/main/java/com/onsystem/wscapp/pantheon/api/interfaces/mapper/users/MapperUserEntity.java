package com.onsystem.wscapp.pantheon.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserDTO;
import com.onsystem.wscapp.pantheon.api.dto.users.UpdateUserDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ISessionManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class MapperUserEntity {

    @Autowired
    ITimeHelper ITimeHelper;

    @Mappings({
            @Mapping(target = "highDate", expression = "java(ITimeHelper.now())"),
            @Mapping(target = "userAttribute", ignore = true),
            @Mapping(target = "deleteDate", ignore = true),
            @Mapping(target = "deleteIdUser", ignore = true),
            @Mapping(target = "idUser", ignore = true),
            @Mapping(target = "permission", ignore = true),
            @Mapping(target = "role", ignore = true)
    })
    public abstract UserEntity createToEntity(CreateUserDTO createUser);


    public abstract CreateAfterUserDTO entityToCreateAfter(UserEntity user);

    @Mappings({
            @Mapping(target = "highDate", source = "userEntity.highDate"),
            @Mapping(target = "highIdUser", source = "userEntity.highIdUser"),
            @Mapping(target = "deleteDate", source = "userEntity.deleteDate"),
            @Mapping(target = "deleteIdUser", source = "userEntity.deleteIdUser"),
            @Mapping(target = "idUser",source = "updateUserDTO.idUser"),
            @Mapping(target = "email",source = "updateUserDTO.email"),
            @Mapping(target = "login",source = "updateUserDTO.login"),
            @Mapping(target = "name",source = "updateUserDTO.name"),
            @Mapping(target = "surname",source = "updateUserDTO.surname"),
            @Mapping(target = "userAttribute", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "permission", ignore = true),
            @Mapping(target = "password", ignore = true)
    })
    public abstract UserEntity entityToUpdate(UpdateUserDTO updateUserDTO, UserEntity userEntity);

}
