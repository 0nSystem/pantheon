package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
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
public abstract class MapperApplicationLanguageEntity {


    @Mappings({
            @Mapping(source = "applicationId", target = "idApplication"),
            @Mapping(source = "createApplicationLanguageDTO.idLanguage", target = "idLanguage"),
    })
    public abstract ApplicationLanguageEntity toEntity(final CreateApplicationLanguageDTO createApplicationLanguageDTO,
                                                       final @NotNull Integer applicationId);


    public abstract ApplicationLanguageDTO toDto(ApplicationLanguageEntity applicationLanguageEntity);

}
