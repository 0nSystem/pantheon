package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;


import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface IMapperUser {


    @Mapping(target = "password", ignore = true)
    UserInfoDTO toDto(UserInfoProjection userInfoProjection);
}
