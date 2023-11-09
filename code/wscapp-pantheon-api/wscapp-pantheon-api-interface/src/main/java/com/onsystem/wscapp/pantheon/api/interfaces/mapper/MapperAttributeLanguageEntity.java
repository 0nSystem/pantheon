package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageKeyEntity;

public class MapperAttributeLanguageEntity {

    public static From<AttributeLanguageEntity, CreateAttributeLanguageDTO> mapperAttributeLanguageEntityFromCreateAttributeLanguageDTO(final int attributeId) {
        return createAttributeLanguageDTO -> AttributeLanguageEntity.builder()
                .attributeLanguageKeyEntity(AttributeLanguageKeyEntity.builder()
                        .idAttribute(attributeId).idLanguage(createAttributeLanguageDTO.getIdLanguage()).build())
                .name(createAttributeLanguageDTO.getName())
                .description(createAttributeLanguageDTO.getDescription())
                .build();
    }

    public static From<AttributeLanguageDTO, AttributeLanguageEntity> mapperAttributeLanguageDTOFromAttributeEntity(){
        return attributeLanguageEntity -> AttributeLanguageDTO.builder()
                .idAttribute(attributeLanguageEntity.getAttributeLanguageKeyEntity().getIdAttribute())
                .idLanguage(attributeLanguageEntity.getAttributeLanguageKeyEntity().getIdLanguage())
                .name(attributeLanguageEntity.getName())
                .description(attributeLanguageEntity.getDescription())
                .build();
    }
}
