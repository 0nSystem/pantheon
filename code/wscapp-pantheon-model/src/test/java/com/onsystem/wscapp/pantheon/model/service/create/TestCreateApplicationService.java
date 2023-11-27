package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
public class TestCreateApplicationService {
    @Autowired
    private ICreateApplicationService iCreateApplicationService;

    @Autowired
    private Integer idLanguage;

    @Test
    public void createApplication() {
        final var createApplicationModel = MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK;
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
        final Integer idApplication = iCreateApplicationService.createApplication(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK).getIdApplication();

        final var applicationLanguage = iCreateApplicationService.createApplicationLanguages(idApplication,
                        Set.of(
                                MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK
                                        .idLanguage(idLanguage).build()
                        )
                ).stream()
                .findFirst().orElseThrow();

        Assertions.assertEquals(idApplication, applicationLanguage.getIdApplication());
        Assertions.assertEquals(idLanguage, applicationLanguage.getIdLanguage());
        Assertions.assertEquals(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK.build().getName(), applicationLanguage.getName());
        Assertions.assertEquals(MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_LANGUAGE_MOCK.build().getDescription(), applicationLanguage.getDescription());
    }
}
