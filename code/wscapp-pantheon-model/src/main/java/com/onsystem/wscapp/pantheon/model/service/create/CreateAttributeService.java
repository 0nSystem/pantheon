package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.dto.attribute.*;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperAttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateAttributeService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final @Positive int applicationId, final Collection<CreateAttributeDTO> createAttribute) {

        final Set<AttributeWithLanguagesDTO> attributeWithLanguages = createAttribute.stream()
                .map(attribute -> {
                    final AttributeEntity attributeEntityMapped = mapperAttributeEntity.toEntity(attribute, applicationId);
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
    public Set<AttributeLanguageDTO> createAttributesLanguages(final @Positive int attributeId, final Collection<CreateAttributeLanguageDTO> createAttribute) {

        final Set<AttributeLanguageEntity> attributeLanguageEntitiesMapped = createAttribute.stream()
                .map(createAttributeLanguageDto -> mapperAttributeLanguageEntity.toEntity(createAttributeLanguageDto, attributeId))
                .collect(Collectors.toSet());

        final List<AttributeLanguageEntity> attributeLanguageEntitiesInsert = attributeLanguageRepository.saveAll(attributeLanguageEntitiesMapped);

        return attributeLanguageEntitiesInsert.stream()
                .map(attributeLanguageEntity -> mapperAttributeLanguageEntity.toDto(attributeLanguageEntity))
                .collect(Collectors.toSet());
    }
}
