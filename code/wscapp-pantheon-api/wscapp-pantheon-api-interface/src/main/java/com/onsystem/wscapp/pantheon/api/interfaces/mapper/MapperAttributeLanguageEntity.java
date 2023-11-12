package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageKeyEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
@Component
public abstract class MapperAttributeLanguageEntity {


    @Mappings({
            @Mapping(source = "attributeId", target = "idAttribute")
    })
    public abstract AttributeLanguageEntity toEntity(final CreateAttributeLanguageDTO createAttributeLanguageDTO,
                                                     final @NotNull Integer attributeId);


    public abstract AttributeLanguageDTO toDto(final AttributeLanguageEntity attributeLanguageEntity);


}
