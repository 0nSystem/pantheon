package com.onsystem.wscapp.pantheon.model.service.applications.create;


import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.CreateAttributeWithLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateAttributeService;
import com.onsystem.wscapp.pantheon.model.service.applications.ThrowingConsumerDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Transactional
class TestCreateAttributeService {

    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idAttribute;
    @Autowired
    private Integer idLanguage;

    @Autowired
    private ICreateAttributeService iCreateAttributeService;


    @TestFactory
    Stream<DynamicTest> createAttribute() {
        final CreateAttributeDTO createAttribute = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_MOCK;

        final Set<AttributeDTO> attributesInserted = iCreateAttributeService.createAttributes(idApplication, Set.of(createAttribute));

        return DynamicTest.stream(attributesInserted.stream(),
                attribute -> String.format("Id: %s, IdApp %s,", attribute.getIdAttribute(), attribute.getIdApplication()),
                ThrowingConsumerDTO.caseDefaultCorrectCreateAttribute(idApplication)
        );
    }

    @TestFactory
    Stream<DynamicTest> createAttributeWithLanguages() {
        final CreateAttributeLanguageDTO createAttributeLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER
                .idLanguage(idLanguage)
                .build();

        final CreateAttributeWithLanguageDTO createAttributeWithLanguage = CreateAttributeWithLanguageDTO.builder()
                .attribute(MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_MOCK)
                .attributeLanguages(Set.of(createAttributeLanguage))
                .build();

        final var attributesWithLanguagesDto = iCreateAttributeService.createAttributesWithLanguages(idApplication, Set.of(createAttributeWithLanguage));

        return attributesWithLanguagesDto.stream()
                .map(attributeWithLanguages -> {
                    final List<DynamicTest> innerTestFromEntity = new ArrayList<>();
                    innerTestFromEntity.add(
                            DynamicTest.dynamicTest(
                                    String.format("Create Attribute id: %s, applicationId: %s",
                                            attributeWithLanguages.getAttribute().getIdAttribute(),
                                            attributeWithLanguages.getAttribute().getIdApplication()),
                                    () -> {
                                        ThrowingConsumerDTO.caseDefaultCorrectCreateAttribute(idApplication).accept(attributeWithLanguages.getAttribute());
                                    }
                            )
                    );

                    innerTestFromEntity.addAll(
                            DynamicTest.stream(
                                    attributeWithLanguages.getAttributeLanguages().stream(),
                                    attributeLanguage -> String.format("Create Attribute Language IdAttribute: %s , IdLanguage :%s", attributeLanguage.getIdAttribute(), attributeLanguage.getIdLanguage()),
                                    ThrowingConsumerDTO.caseDefaultCorrectCreateAttributeLanguage(attributeWithLanguages.getAttribute().getIdAttribute(), idLanguage)
                            ).toList()
                    );


                    return innerTestFromEntity;
                })
                .flatMap(Collection::stream);
    }

    @TestFactory
    Stream<DynamicTest> createAttributeLanguage() {
        final CreateAttributeLanguageDTO createAttributeLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER
                .idLanguage(idLanguage)
                .build();

        final var attributesLanguagesDto = iCreateAttributeService.createAttributesLanguages(idAttribute, Set.of(createAttributeLanguage));

        return DynamicTest.stream(attributesLanguagesDto.stream(),
                attributeLanguage -> String.format("IdAttribute: %s , IdLanguage :%s", attributeLanguage.getIdAttribute(), attributeLanguage.getIdLanguage()),
                ThrowingConsumerDTO.caseDefaultCorrectCreateAttributeLanguage(idAttribute, idLanguage));
    }




}
