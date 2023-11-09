package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageKeyEntity;

import java.util.function.Function;

public class MapperAttributeLanguageEntity {

    public static Function<CreateAttributeLanguageDTO, AttributeLanguageEntity> mapperAttributeLanguageEntityFromCreateAttributeLanguageDTO(final int attributeId) {
        return createAttributeLanguageDTO -> AttributeLanguageEntity.builder()
                .attributeLanguageKeyEntity(AttributeLanguageKeyEntity.builder()
                        .idAttribute(attributeId).idLanguage(createAttributeLanguageDTO.getIdLanguage()).build())
                .name(createAttributeLanguageDTO.getName())
                .description(createAttributeLanguageDTO.getDescription())
                .build();
    }

    public static Function<AttributeLanguageEntity, AttributeLanguageDTO> mapperAttributeLanguageDTOFromAttributeEntity() {
        return attributeLanguageEntity -> AttributeLanguageDTO.builder()
                .idAttribute(attributeLanguageEntity.getAttributeLanguageKeyEntity().getIdAttribute())
                .idLanguage(attributeLanguageEntity.getAttributeLanguageKeyEntity().getIdLanguage())
                .name(attributeLanguageEntity.getName())
                .description(attributeLanguageEntity.getDescription())
                .build();
    }
}
