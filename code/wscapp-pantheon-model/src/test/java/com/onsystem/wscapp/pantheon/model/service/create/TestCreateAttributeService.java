package com.onsystem.wscapp.pantheon.model.service.create;


import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeWithLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateAttributeService;
import com.onsystem.wscapp.pantheon.model.service.ThrowingConsumerDTO;
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
public class TestCreateAttributeService {

    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idAttribute;
    @Autowired
    private Integer idLanguage;

    @Autowired
    private ICreateAttributeService iCreateAttributeService;


    @TestFactory
    public Stream<DynamicTest> createAttribute() {
        final CreateAttributeDTO createAttribute = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_MOCK;

        final Set<AttributeDTO> attributesInserted = iCreateAttributeService.createAttributes(idApplication, Set.of(createAttribute));

        return DynamicTest.stream(attributesInserted.stream(),
                attribute -> String.format("Id: %s, IdApp %s,", attribute.getIdAttribute(), attribute.getIdApplication()),
                ThrowingConsumerDTO.caseDefaultCorrectCreateAttribute(idApplication)
        );
    }

    @TestFactory
    public Stream<DynamicTest> createAttributeWithLanguages() {
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
    public Stream<DynamicTest> createAttributeLanguage() {
        final CreateAttributeLanguageDTO createAttributeLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER
                .idLanguage(idLanguage)
                .build();

        final var attributesLanguagesDto = iCreateAttributeService.createAttributesLanguages(idAttribute, Set.of(createAttributeLanguage));

        return DynamicTest.stream(attributesLanguagesDto.stream(),
                attributeLanguage -> String.format("IdAttribute: %s , IdLanguage :%s", attributeLanguage.getIdAttribute(), attributeLanguage.getIdLanguage()),
                ThrowingConsumerDTO.caseDefaultCorrectCreateAttributeLanguage(idAttribute, idLanguage));
    }




}
