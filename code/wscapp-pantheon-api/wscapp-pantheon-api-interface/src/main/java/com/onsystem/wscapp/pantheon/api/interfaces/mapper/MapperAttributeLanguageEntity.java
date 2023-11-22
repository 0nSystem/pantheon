package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperAttributeLanguageEntity {


    @Mappings({
            @Mapping(target = "attribute.idAttribute", source = "attributeId"),
            @Mapping(target = "language.idLanguage", source = "createAttributeLanguageDTO.idLanguage")
    })
    public abstract AttributeLanguageEntity toEntity(final CreateAttributeLanguageDTO createAttributeLanguageDTO,
                                                     final @NotNull Integer attributeId);

    @Mappings({
            @Mapping(target = "idAttribute", source = "attribute.idAttribute"),
            @Mapping(target = "idLanguage", source = "language.idLanguage")
    })
    public abstract AttributeLanguageDTO toDto(final AttributeLanguageEntity attributeLanguageEntity);


}
