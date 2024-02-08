package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AttributeInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.AttributeUserDataDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface IMapperAttribute {


    AttributeInfoDTO toDto(final AttributeInfoProjection attributeInfoProjection);


}
