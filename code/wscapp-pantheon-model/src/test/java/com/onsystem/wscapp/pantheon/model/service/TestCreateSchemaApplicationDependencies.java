package com.onsystem.wscapp.pantheon.model.service;


import com.onsystem.wscapp.pantheon.api.interfaces.repositories.LanguageRepository;
import com.onsystem.wscapp.pantheon.model.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCreateSchemaApplicationDependencies {

    @Autowired
    private CreateSchemaApplicationDependencies createSchemaApplicationDependencies;
    @Autowired
    private LanguageRepository languageRepository;


    @Test
    public void createApplication() {
        final var createApplicationModel = MockData.DataMockSchemeApplication.createApplicationModel;
        final var applicationCreateDTO = createSchemaApplicationDependencies.createApplication(createApplicationModel);

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
    public void createFullApplication() {


        /*
        final CreateFullApplicationDTO createFullApplicationDTO = CreateFullApplicationDTO.builder()
                .application(createApplicationModel)
                .applicationLanguages(createApplicationLanguage)
                .build();


        final var applicationInserted = createSchemaApplicationDependencies.createFullApplication(createFullApplicationDTO);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(applicationInserted);
            Assertions.assertTrue(applicationInserted.getApplication().getIdApplication() > 0);

            Assertions.assertEquals(createFullApplicationDTO.getApplication().getName(), applicationInserted.getApplication().getName());
            Assertions.assertEquals(createFullApplicationDTO.getApplication().getDescription(), applicationInserted.getApplication().getDescription());

            Assertions.assertNull(applicationInserted.getApplication().getDeleteIdUser());
            Assertions.assertNull(applicationInserted.getApplication().getDeleteDate());

            Assertions.assertNotNull(applicationInserted.getApplication().getHighDate());
            Assertions.assertEquals(createFullApplicationDTO.getApplication().getHighIdUser(), applicationInserted.getApplication().getHighIdUser());

        });
         */

    }

}
