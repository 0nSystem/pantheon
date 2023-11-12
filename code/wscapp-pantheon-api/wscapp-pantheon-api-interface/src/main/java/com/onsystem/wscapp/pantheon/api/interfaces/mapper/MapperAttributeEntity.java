package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
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
public abstract class MapperAttributeEntity {

    @Mappings({
            @Mapping(source = "applicationId", target = "idApplication"),
            @Mapping(target = "idAttribute", ignore = true)
    })
    public abstract AttributeEntity toEntity(final CreateAttributeDTO createAttributeDTO,
                                             final @NotNull Integer applicationId);


    public abstract AttributeDTO toDto(final AttributeEntity attributeEntity);
}
