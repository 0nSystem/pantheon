package com.onsystem.wscapp.pantheon.model.service.update;

import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.ArgumentsParams;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdateApplicationService;
import com.onsystem.wscapp.pantheon.model.service.ThrowingConsumerDTO;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
public class TestUpdateApplicationService {

    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private IUpdateApplicationService iUpdateApplicationService;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;


    @ParameterizedTest
    @MethodSource({"com.onsystem.wscapp.pantheon.api.interfaces.ArgumentsParams#argumentsUpdateApplication"})
    void updateApplication(final UpdateApplicationDTO updateApplication) throws Throwable {


        iUpdateApplicationService.updateApplication(Set.of(updateApplication));

        final ApplicationEntity applicationEntity = applicationRepository.findById(updateApplication.getIdApplication())
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdateApplication(updateApplication)
                .accept(applicationEntity);

    }


    @ParameterizedTest
    @MethodSource({"com.onsystem.wscapp.pantheon.api.interfaces.ArgumentsParams#argumentsUpdateApplicationLanguage"})
    void updateApplicationLanguages(final UpdateApplicationLanguageDTO updateApplicationLanguage) throws Throwable {

        iCreateApplicationService.createApplicationLanguages(updateApplicationLanguage.getIdApplication(),
                Set.of(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK
                        .idLanguage(updateApplicationLanguage.getIdLanguage()).build()));


        iUpdateApplicationService.updateApplicationLanguages(Set.of(updateApplicationLanguage));

        final ApplicationLanguageEntity applicationLanguage = applicationLanguageRepository.findById(
                ApplicationLanguageKeyEntity.builder()
                        .application(updateApplicationLanguage.getIdApplication())
                        .language(updateApplicationLanguage.getIdLanguage()).build()
        ).orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdateApplicationLanguage(updateApplicationLanguage)
                .accept(applicationLanguage);

    }


}