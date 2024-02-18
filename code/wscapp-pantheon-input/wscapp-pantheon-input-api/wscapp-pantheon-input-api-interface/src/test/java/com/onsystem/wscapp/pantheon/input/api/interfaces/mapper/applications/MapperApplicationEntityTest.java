package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.ISessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MapperApplicationEntityTest {

    @Autowired
    private MapperApplicationEntity mapper;
    @Autowired
    private ITimeHelper ITimeHelper;
    @Autowired
    private ISessionManager iSessionManager;


    @Test
    void testCreateEntityToEntityAndAssignHighDate() {
        final CreateApplicationDTO createApplicationDTO = MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK;


        final ApplicationEntity applicationEntity = mapper
                .createToEntity(createApplicationDTO);

        Assertions.assertNotNull(applicationEntity);
        Assertions.assertEquals(createApplicationDTO.getName(), applicationEntity.getName());
        Assertions.assertEquals(createApplicationDTO.getDescription(), applicationEntity.getDescription());
        createApplicationDTO.getHighIdUser()
                .ifPresentOrElse(integer -> Assertions.assertEquals(integer, applicationEntity.getHighIdUser()),
                        () -> Assertions.assertEquals(iSessionManager.currentIdUser(), applicationEntity.getHighIdUser()));

        Assertions.assertNotNull(applicationEntity.getHighDate());

    }

    @Test
    void testCreateEntityToEntityReturnNull() {
        final ApplicationEntity applicationEntity = mapper
                .createToEntity(null);

        Assertions.assertNull(applicationEntity);
    }

    @Test
    void testEntityToDTO() {
        final ApplicationEntity entity = ApplicationEntity.builder()
                .idApplication(1)
                .name("Name")
                .description("Description")
                .highIdUser(1)
                .highDate(ITimeHelper.now())
                .deleteDate(ITimeHelper.now())
                .deleteIdUser(1)
                .build();


        final ApplicationDTO applicationDTO = mapper
                .entityToDTO(entity);

        Assertions.assertNotNull(applicationDTO);
        Assertions.assertEquals(entity.getIdApplication(), applicationDTO.getIdApplication());
        Assertions.assertEquals(entity.getName(), applicationDTO.getName());
        Assertions.assertEquals(entity.getDescription(), applicationDTO.getDescription());
        Assertions.assertEquals(entity.getHighIdUser(), applicationDTO.getHighIdUser());
        Assertions.assertEquals(entity.getHighDate(), applicationDTO.getHighDate());
        Assertions.assertEquals(entity.getDeleteDate(), applicationDTO.getDeleteDate());
        Assertions.assertEquals(entity.getDeleteIdUser(), applicationDTO.getDeleteIdUser());


    }

    @Test
    void testEntityToDTOReturnNull() {
        final ApplicationDTO applicationDTO = mapper
                .entityToDTO(null);

        Assertions.assertNull(applicationDTO);
    }


}
