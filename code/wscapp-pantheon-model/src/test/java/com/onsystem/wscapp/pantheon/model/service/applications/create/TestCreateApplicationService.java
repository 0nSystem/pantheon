package com.onsystem.wscapp.pantheon.model.service.applications.create;

import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateApplicationService;
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
    private PermissionRepository permissionRepository;

    @Autowired
    private Integer idLanguage;

    @Test
    void createApplication() {
        final var createApplicationModel = MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK;
        final var applicationCreateDTO = iCreateApplicationService.createApplication(createApplicationModel);

        //Is list but require initialize with "authorized-permission" by default to add users
        final var permissionApplication = permissionRepository.findByApplicationIdApplication(applicationCreateDTO.getIdApplication());

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(applicationCreateDTO);
            Assertions.assertTrue(applicationCreateDTO.getIdApplication() > 0);

            Assertions.assertEquals(createApplicationModel.getName(), applicationCreateDTO.getName());
            Assertions.assertEquals(createApplicationModel.getDescription(), applicationCreateDTO.getDescription());

            Assertions.assertNull(applicationCreateDTO.getDeleteIdUser());
            Assertions.assertNull(applicationCreateDTO.getDeleteDate());

            Assertions.assertNotNull(applicationCreateDTO.getHighDate());
            Assertions.assertEquals(createApplicationModel.getHighIdUser(), applicationCreateDTO.getHighIdUser());

            Assertions.assertEquals(1,permissionApplication.size());

        });
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

        final var permissionApplication = permissionRepository.findByApplicationIdApplication(idApplication);

        Assertions.assertEquals(idApplication, applicationLanguage.getIdApplication());
        Assertions.assertEquals(idLanguage, applicationLanguage.getIdLanguage());
        Assertions.assertEquals(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK.build().getName(), applicationLanguage.getName());
        Assertions.assertEquals(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK.build().getDescription(), applicationLanguage.getDescription());
        Assertions.assertEquals(1,permissionApplication.size());


    }
}
