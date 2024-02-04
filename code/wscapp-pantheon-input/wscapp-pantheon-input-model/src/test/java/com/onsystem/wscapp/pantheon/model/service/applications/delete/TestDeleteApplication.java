package com.onsystem.wscapp.pantheon.model.service.applications.delete;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.delete.IDeleteApplicationService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Transactional
class TestDeleteApplication {

    @Autowired
    private IDeleteApplicationService iDeleteApplicationService;
    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;
    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idLanguage;


    private Stream<Arguments> argumentsDeleteApplication() {
        return Stream.of(
                Arguments.of(1, idApplication)
        );
    }

    @Rollback
    @ParameterizedTest
    @MethodSource("argumentsDeleteApplication")
    void testDeleteApplication(final @Positive int idUser, final @Positive int idApplication) {
        iDeleteApplicationService.deleteApplication(idUser, List.of(idApplication));

        final ApplicationEntity applicationEntity = applicationRepository.findById(idApplication)
                .orElseThrow();

        Assertions.assertNotNull(applicationEntity.getDeleteDate());
        Assertions.assertNotNull(applicationEntity.getDeleteIdUser());
    }

    private Stream<Arguments> argumentsDeleteApplicationLanguage() {
        return Stream.of(
                Arguments.of(idApplication, idLanguage)
        );
    }

    @Rollback
    @ParameterizedTest
    @MethodSource("argumentsDeleteApplicationLanguage")
    void testDeleteApplicationLanguage(final @Positive int idApplication, final Integer languageId) {


        final CreateApplicationLanguageDTO createApplicationLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK
                .idLanguage(languageId)
                .build();
        iCreateApplicationService.createApplicationLanguages(idApplication, List.of(createApplicationLanguage));

        final long numberApplicationLanguagesBeforeDelete = applicationLanguageRepository.count();

        iDeleteApplicationService.deleteApplicationLanguage(idApplication, List.of(languageId));

        final long numberApplicationLanguagesAfterDelete = applicationLanguageRepository.count();

        Assertions.assertEquals(numberApplicationLanguagesBeforeDelete - 1, numberApplicationLanguagesAfterDelete);
    }
}
