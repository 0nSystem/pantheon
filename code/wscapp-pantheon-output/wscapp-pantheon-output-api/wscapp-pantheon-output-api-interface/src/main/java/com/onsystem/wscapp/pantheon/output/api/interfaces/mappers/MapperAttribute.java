package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;

import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.AttributeInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MapperAttribute {


    AttributeInfoDTO toDto(final AttributeEntity attributeEntity);
}
