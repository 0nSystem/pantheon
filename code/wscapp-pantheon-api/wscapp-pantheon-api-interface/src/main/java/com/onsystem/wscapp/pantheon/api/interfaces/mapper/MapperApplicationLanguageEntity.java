package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
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
