package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;

import java.util.function.Function;

public class MapperAttributeEntity {


    public static Function<CreateAttributeDTO, AttributeEntity> toEntity(final int applicationId) {
        return createAttributeDTO -> AttributeEntity.builder()
                .idApplication(applicationId)
                .name(createAttributeDTO.getName())
                .description(createAttributeDTO.getDescription())
                .build();
    }

    public static Function<AttributeEntity, AttributeDTO> toDto() {
        return attributeEntity -> AttributeDTO.builder()
                .idAttribute(attributeEntity.getIdAttribute())
                .idApplication(attributeEntity.getIdApplication())
                .name(attributeEntity.getName())
                .description(attributeEntity.getDescription())
                .build();
    }
}
