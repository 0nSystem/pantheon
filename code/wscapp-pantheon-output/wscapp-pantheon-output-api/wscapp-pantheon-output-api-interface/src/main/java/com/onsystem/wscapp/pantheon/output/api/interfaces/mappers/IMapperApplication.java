package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;



import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.ApplicationInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface IMapperApplication {


    ApplicationInfoDTO toDto(
            ApplicationInfoProjection applicationInfoProjection
    );


}
