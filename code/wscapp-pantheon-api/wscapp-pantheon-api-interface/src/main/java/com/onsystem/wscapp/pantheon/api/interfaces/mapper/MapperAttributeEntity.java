package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;

public class MapperAttributeEntity {


    public static From<AttributeEntity, CreateAttributeDTO> mapperAttributeEntityFromCreateAttributeDTO(final int applicationId) {
        return createAttributeDTO -> AttributeEntity.builder()
                .idApplication(applicationId)
                .name(createAttributeDTO.getName())
                .description(createAttributeDTO.getDescription())
                .build();
    }

    public static From<AttributeDTO, AttributeEntity> mapperAttributeDTOFromAttribute(){
        return attributeEntity -> AttributeDTO.builder()
                .idAttribute(attributeEntity.getIdAttribute())
                .idApplication(attributeEntity.getIdApplication())
                .name(attributeEntity.getName())
                .description(attributeEntity.getDescription())
                .build();
    }
}
