package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.ApplicationLanguageEntity;
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
            @Mapping(source = "createApplicationLanguageDTO.idLanguage", target = "language.idLanguage"), //TODO
            @Mapping(source = "createApplicationLanguageDTO.name", target = "name"),
            @Mapping(source = "createApplicationLanguageDTO.description", target = "description"),
            @Mapping(source = "applicationId", target = "application.idApplication"),
    })
    public abstract ApplicationLanguageEntity createToEntity(final CreateApplicationLanguageDTO createApplicationLanguageDTO,
                                                             final Integer applicationId);


    @Mappings({
            @Mapping(source = "application.idApplication", target = "idApplication"),
            @Mapping(source = "language.idLanguage", target = "idLanguage"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
    })
    public abstract ApplicationLanguageDTO toDto(ApplicationLanguageEntity applicationLanguageEntity);

}
