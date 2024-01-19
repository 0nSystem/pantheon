package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.AttributeEntity;
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
public abstract class MapperAttributeEntity {

    @Mappings({
            @Mapping(target = "application.idApplication", source = "applicationId"),
            @Mapping(target = "idAttribute", ignore = true),
            @Mapping(target = "attributeLanguages", ignore = true),
            @Mapping(target = "userAttribute", ignore = true)
    })
    public abstract AttributeEntity createToEntity(final CreateAttributeDTO createAttributeDTO,
                                                   final @NotNull Integer applicationId);


    @Mappings({
            @Mapping(target = "idApplication", source = "application.idApplication")
    })
    public abstract AttributeDTO toDto(final AttributeEntity attributeEntity);
}
