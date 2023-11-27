package com.onsystem.wscapp.pantheon.model.service.update;

import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateAttributeService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdateAttributeService;
import com.onsystem.wscapp.pantheon.model.service.ThrowingConsumerDTO;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
public class TestUpdateAttribute {

    @Autowired
    private ICreateAttributeService iCreateAttributeService;
    @Autowired
    private IUpdateAttributeService iUpdateAttributeService;

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;

    @Autowired
    private Integer idAttribute;
    @Autowired
    private Integer idLanguage;
    @Autowired
    private Integer idApplication;


    private Stream<Arguments> argumentsUpdateAttribute() {
        return Stream.of(
                Arguments.of(MockData.DataUpdateMockSchemeApplicationDTO.UPDATE_ATTRIBUTE_MOCK_BUILDER
                        .idAttribute(idAttribute)
                        .build())
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsUpdateAttribute")
    public void updateAttribute(final @NotNull UpdateAttributeDTO updateAttributeDTO) throws Throwable {

        iUpdateAttributeService.updateAttribute(Set.of(updateAttributeDTO));

        final AttributeEntity attributeEntity = attributeRepository.findById(updateAttributeDTO.getIdAttribute())
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdateAttribute(updateAttributeDTO)
                .accept(attributeEntity);
    }

    private Stream<Arguments> argumentsUpdateAttributeLanguage() {
        return Stream.of(
                Arguments.of(MockData.DataUpdateMockSchemeApplicationDTO.UPDATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER
                        .idAttribute(idAttribute)
                        .idLanguage(idLanguage)
                        .build())
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsUpdateAttributeLanguage")
    public void updateAttribute(final @NotNull UpdateAttributeLanguageDTO updateAttributeLanguageDTO) throws Throwable {
        iCreateAttributeService.createAttributesLanguages(idApplication,
                Set.of(
                        MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER
                                .idLanguage(updateAttributeLanguageDTO.getIdLanguage()).build()
                )
        );

        iUpdateAttributeService.updateAttributeLanguages(Set.of(updateAttributeLanguageDTO));

        final AttributeLanguageEntity attributeLanguageEntity = attributeLanguageRepository.findById(
                AttributeLanguageKeyEntity.builder()
                        .attribute(updateAttributeLanguageDTO.getIdAttribute())
                        .language(updateAttributeLanguageDTO.getIdLanguage())
                        .build()
        ).orElseThrow();


        ThrowingConsumerDTO.caseDefaultCorrectUpdateAttributeLanguage(updateAttributeLanguageDTO)
                .accept(attributeLanguageEntity);
    }
}
