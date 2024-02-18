package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.ISessionManager;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class MapperUserEntity {

    @Autowired
    ITimeHelper ITimeHelper;
    @Autowired
    ISessionManager iSessionManager;

    @Mappings({
            @Mapping(target = "highDate", expression = "java(ITimeHelper.now())"),
            @Mapping(target = "highIdUser", ignore = true),
            @Mapping(target = "userAttribute", ignore = true),
            @Mapping(target = "deleteDate", ignore = true),
            @Mapping(target = "deleteIdUser", ignore = true),
            @Mapping(target = "idUser", ignore = true),
            @Mapping(target = "permission", ignore = true),
            @Mapping(target = "role", ignore = true)
    })
    public abstract UserEntity createToEntity(CreateUserDTO createUser);

    @AfterMapping
    void handlerOptionalHighIdUser(@MappingTarget UserEntity userEntity, CreateUserDTO createUserDTO) {
        // Maneja el campo Optional aquí según tus necesidades
        createUserDTO.getHighIdUser().ifPresentOrElse(
                userEntity::setHighIdUser,
                () -> userEntity.setHighIdUser(iSessionManager.currentIdUser()));
    }


    @Mappings({
            @Mapping(target = "highDate", source = "userEntity.highDate"),
            @Mapping(target = "highIdUser", source = "userEntity.highIdUser"),
            @Mapping(target = "deleteDate", source = "userEntity.deleteDate"),
            @Mapping(target = "deleteIdUser", source = "userEntity.deleteIdUser"),
            @Mapping(target = "idUser", source = "updateUserDTO.idUser"),
            @Mapping(target = "email", source = "updateUserDTO.email"),
            @Mapping(target = "login", source = "updateUserDTO.login"),
            @Mapping(target = "name", source = "updateUserDTO.name"),
            @Mapping(target = "surname", source = "updateUserDTO.surname"),
            @Mapping(target = "password", source = "userEntity.password"), //TODO create update custom to user entity ...
            @Mapping(target = "userAttribute", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "permission", ignore = true),
    })
    public abstract UserEntity entityToUpdate(UpdateUserDTO updateUserDTO, UserEntity userEntity);

    public abstract CreateAfterUserDTO entityToCreateAfter(UserEntity user);

}
