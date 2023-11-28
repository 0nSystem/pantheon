package com.onsystem.wscapp.pantheon.model.service.delete;

import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateAttributeService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.delete.IDeleteAttributeService;
import jakarta.validation.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Rollback
class TestDeleteAttributeService {
    @Autowired
    private ICreateAttributeService iCreateAttributeService;
    @Autowired
    private IDeleteAttributeService iDeleteAttributeService;
    @Autowired
    private AttributeRepository attributeRepository;


    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idLanguage;
    @Autowired
    private Integer idAttribute;
    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;

    private Stream<Arguments> argumentsDeleteApplication() {
        return Stream.of(Arguments.of(idApplication));
    }

    @ParameterizedTest
    @MethodSource("argumentsDeleteApplication")
    void testDeleteApplication(final @Positive int applicationId) {

        final CreateAttributeDTO createAttributeDTO = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_MOCK;

        final List<Integer> idsAttributes = iCreateAttributeService.createAttributes(applicationId, Set.of(createAttributeDTO)).stream().map(AttributeDTO::getIdAttribute).toList();

        final long countAttributeBeforeDelete = attributeRepository.count();

        iDeleteAttributeService.deleteAttribute(idsAttributes);

        final long countAttributeAfterDelete = attributeRepository.count();

        Assertions.assertEquals(countAttributeBeforeDelete - 1, countAttributeAfterDelete);
    }


    private Stream<Arguments> argumentsDeleteApplicationLanguage() {
        return Stream.of(Arguments.of(idAttribute, idLanguage));
    }

    @ParameterizedTest
    @MethodSource("argumentsDeleteApplicationLanguage")
    void testDeleteApplicationLanguage(final @Positive int attributeId, final @Positive int languageId) {

        final CreateAttributeLanguageDTO createAttributeLanguageDTO = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ATTRIBUTE_LANGUAGE_MOCK_BUILDER.idLanguage(languageId).build();

        iCreateAttributeService.createAttributesLanguages(attributeId, Set.of(createAttributeLanguageDTO));

        final long countAttributeLanguageBeforeDelete = attributeLanguageRepository.count();

        iDeleteAttributeService.deleteAttributeLanguage(attributeId, List.of(languageId));

        final long countAttributeLanguageAfterDelete = attributeLanguageRepository.count();

        Assertions.assertEquals(countAttributeLanguageBeforeDelete - 1, countAttributeLanguageAfterDelete);
    }
}
