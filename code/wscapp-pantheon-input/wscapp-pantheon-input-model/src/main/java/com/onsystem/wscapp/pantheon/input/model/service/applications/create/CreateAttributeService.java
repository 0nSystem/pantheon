package com.onsystem.wscapp.pantheon.input.model.service.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.*;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperAttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperAttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateAttributeService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateAttributeService implements ICreateAttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private MapperAttributeEntity mapperAttributeEntity;

    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;
    @Autowired
    private MapperAttributeLanguageEntity mapperAttributeLanguageEntity;

    @Override
    public Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final @Positive int applicationId, final Set<CreateAttributeWithLanguageDTO> createAttribute) {

        final Set<AttributeWithLanguagesDTO> attributeWithLanguages = createAttribute.stream()
                .map(attribute -> {
                    final AttributeEntity attributeEntityMapped = mapperAttributeEntity.createToEntity(attribute.getAttribute(), applicationId);
                    final AttributeEntity attributeInsert = attributeRepository.save(attributeEntityMapped);
                    final AttributeDTO attributeDTO = mapperAttributeEntity.toDto(attributeInsert);

                    return AttributeWithLanguagesDTO.builder()
                            .attribute(attributeDTO)
                            .attributeLanguages(createAttributesLanguages(attributeInsert.getIdAttribute(), attribute.getAttributeLanguages()))
                            .build();
                })
                .collect(Collectors.toSet());

        return attributeWithLanguages;
    }

    @Override
    public Set<AttributeDTO> createAttributes(final int applicationId, final Set<CreateAttributeDTO> createAttributes) {


        final List<AttributeEntity> attributeEntitiesMapped = createAttributes.stream()
                .map(att -> mapperAttributeEntity.createToEntity(att, applicationId))
                .collect(Collectors.toList());
        final List<AttributeEntity> attributeEntitiesInserted = attributeRepository.saveAll(attributeEntitiesMapped);

        return attributeEntitiesInserted.stream()
                .map(att -> mapperAttributeEntity.toDto(att))
                .collect(Collectors.toSet());

    }


    @Override
    public Set<AttributeLanguageDTO> createAttributesLanguages(final @Positive int attributeId, final Set<CreateAttributeLanguageDTO> createAttribute) {

        final Set<AttributeLanguageEntity> attributeLanguageEntitiesMapped = createAttribute.stream()
                .map(createAttributeLanguageDto -> mapperAttributeLanguageEntity.createToEntity(createAttributeLanguageDto, attributeId))
                .collect(Collectors.toSet());

        final List<AttributeLanguageEntity> attributeLanguageEntitiesInsert = attributeLanguageRepository.saveAll(attributeLanguageEntitiesMapped);

        return attributeLanguageEntitiesInsert.stream()
                .map(attributeLanguageEntity -> mapperAttributeLanguageEntity.toDto(attributeLanguageEntity))
                .collect(Collectors.toSet());
    }
}
