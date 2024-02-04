package com.onsystem.wscapp.pantheon.model.service.applications;


import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.spublic.LanguageRepository;
import com.onsystem.wscapp.pantheon.input.model.service.applications.create.CreateFullApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCreateSchemaApplicationDependencies {

    @Autowired
    private CreateFullApplicationService createSchemaApplicationDependencies;
    @Autowired
    private LanguageRepository languageRepository;


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
