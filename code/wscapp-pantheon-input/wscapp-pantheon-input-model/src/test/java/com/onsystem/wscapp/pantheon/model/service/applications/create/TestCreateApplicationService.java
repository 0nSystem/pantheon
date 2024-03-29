package com.onsystem.wscapp.pantheon.model.service.applications.create;

import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.ISessionManager;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Transactional
class TestCreateApplicationService {
    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ISessionManager iSessionManager;

    @Autowired
    private Integer idLanguage;

    @Test
    void createApplication() {
        final var createApplicationModel = MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK;
        final var applicationCreateDTO = iCreateApplicationService.createApplication(createApplicationModel);

        //Is list but require initialize with "authorized-permission" by default to add users
        final var permissionApplication = roleRepository.findByApplicationIdApplication(applicationCreateDTO.getIdApplication());

        Assertions.assertNotNull(applicationCreateDTO);
        Assertions.assertTrue(applicationCreateDTO.getIdApplication() > 0);

        Assertions.assertEquals(createApplicationModel.getName(), applicationCreateDTO.getName());
        Assertions.assertEquals(createApplicationModel.getDescription(), applicationCreateDTO.getDescription());

        Assertions.assertNull(applicationCreateDTO.getDeleteIdUser());
        Assertions.assertNull(applicationCreateDTO.getDeleteDate());

        Assertions.assertNotNull(applicationCreateDTO.getHighDate());
        createApplicationModel.getHighIdUser()
                .ifPresentOrElse(integer -> Assertions.assertEquals(integer, applicationCreateDTO.getHighIdUser()),
                        () -> Assertions.assertEquals(iSessionManager.currentIdUser(), applicationCreateDTO.getHighIdUser()));

        Assertions.assertEquals(1, permissionApplication.size());
    }


    @Test
    void createApplicationLanguage() {
        final Integer idApplication = iCreateApplicationService.createApplication(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK).getIdApplication();

        final var applicationLanguage = iCreateApplicationService.createApplicationLanguages(idApplication,
                        Set.of(
                                MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK
                                        .idLanguage(idLanguage).build()
                        )
                ).stream()
                .findFirst().orElseThrow();

        final var permissionApplication = roleRepository.findByApplicationIdApplication(idApplication);

        Assertions.assertEquals(idApplication, applicationLanguage.getIdApplication());
        Assertions.assertEquals(idLanguage, applicationLanguage.getIdLanguage());
        Assertions.assertEquals(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK.build().getName(), applicationLanguage.getName());
        Assertions.assertEquals(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK.build().getDescription(), applicationLanguage.getDescription());
        Assertions.assertEquals(1, permissionApplication.size());


    }
}
