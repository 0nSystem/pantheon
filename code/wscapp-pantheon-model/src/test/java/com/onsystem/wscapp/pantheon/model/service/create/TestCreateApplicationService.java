package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.LanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.model.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class TestCreateApplicationService {
    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private LanguageRepository languageRepository;

    @Test
    public void createApplication() {
        final var createApplicationModel = MockData.DataMockSchemeApplication.createApplicationModel;
        final var applicationCreateDTO = iCreateApplicationService.createApplication(createApplicationModel);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(applicationCreateDTO);
            Assertions.assertTrue(applicationCreateDTO.getIdApplication() > 0);

            Assertions.assertEquals(createApplicationModel.getName(), applicationCreateDTO.getName());
            Assertions.assertEquals(createApplicationModel.getDescription(), applicationCreateDTO.getDescription());

            Assertions.assertNull(applicationCreateDTO.getDeleteIdUser());
            Assertions.assertNull(applicationCreateDTO.getDeleteDate());

            Assertions.assertNotNull(applicationCreateDTO.getHighDate());
            Assertions.assertEquals(createApplicationModel.getHighIdUser(), applicationCreateDTO.getHighIdUser());

        });
    }


    @Test
    public void createApplicationLanguage() {
        final Integer idApplication = iCreateApplicationService.createApplication(MockData.DataMockSchemeApplication.createApplicationModel).getIdApplication();
        final Integer idLanguage = languageRepository.save(MockData.DataMockSchemeApplication.language).getIdLanguage();

        final var applicationLanguage = iCreateApplicationService.createApplicationLanguages(idApplication,
                        Set.of(
                                MockData.DataMockSchemeApplication.createApplicationLanguage
                                        .idLanguage(idLanguage).build()
                        )
                ).stream()
                .findFirst().orElseThrow();

        Assertions.assertEquals(idApplication, applicationLanguage.getIdApplication());
        Assertions.assertEquals(idLanguage, applicationLanguage.getIdLanguage());
        Assertions.assertEquals(MockData.DataMockSchemeApplication.createApplicationLanguage.build().getName(), applicationLanguage.getName());
        Assertions.assertEquals(MockData.DataMockSchemeApplication.createApplicationLanguage.build().getDescription(), applicationLanguage.getDescription());
    }
}
