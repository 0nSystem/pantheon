package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.helpers.TimeHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperApplicationEntityTest {

    @Autowired
    private MapperApplicationEntity mapper;
    @Autowired
    private TimeHelper timeHelper;


    @Test
    void testCreateEntityToEntityAndAssignHighDate() {
        final CreateApplicationDTO createApplicationDTO = CreateApplicationDTO.builder()
                .name("Name")
                .description("Description")
                .highIdUser(1)
                .build();


        final ApplicationEntity applicationEntity = mapper
                .createEntityToEntity(createApplicationDTO);

        Assertions.assertNotNull(applicationEntity);
        Assertions.assertEquals(createApplicationDTO.getName(), applicationEntity.getName());
        Assertions.assertEquals(createApplicationDTO.getDescription(), applicationEntity.getDescription());
        Assertions.assertEquals(createApplicationDTO.getHighIdUser(), applicationEntity.getHighIdUser());
        Assertions.assertNotNull(applicationEntity.getHighDate());

    }
    @Test
    void testCreateEntityToEntityReturnNull(){
        final ApplicationEntity applicationEntity = mapper
                .createEntityToEntity(null);

        Assertions.assertNull(applicationEntity);
    }

    @Test
    void testEntityToDTO() {
        final ApplicationEntity entity = ApplicationEntity.builder()
                .idApplication(1)
                .name("Name")
                .description("Description")
                .highIdUser(1)
                .highDate(timeHelper.now())
                .deleteDate(timeHelper.now())
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
    void testEntityToDTOReturnNull(){
        final ApplicationDTO applicationDTO = mapper
                .entityToDTO(null);

        Assertions.assertNull(applicationDTO);
    }


}
