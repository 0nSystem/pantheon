package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperApplicationLanguageEntityTest {


    @Autowired
    private MapperApplicationLanguageEntity mapperApplicationLanguageEntity;

    @Test
    public void testCreateApplicationLanguageToEntity() {
        final Integer applicationId = 1;
        final CreateApplicationLanguageDTO createApplicationLanguageDTO = CreateApplicationLanguageDTO.builder()
                .name("asd")
                .description("asd")
                .idLanguage(1)
                .build();

        final ApplicationLanguageEntity applicationLanguageEntity = mapperApplicationLanguageEntity.toEntity(createApplicationLanguageDTO, applicationId);

        Assertions.assertNotNull(applicationLanguageEntity);
        Assertions.assertEquals(createApplicationLanguageDTO.getIdLanguage(), applicationLanguageEntity.getIdLanguage());
        Assertions.assertEquals(applicationId, applicationLanguageEntity.getIdApplication());
        Assertions.assertEquals(createApplicationLanguageDTO.getName(), applicationLanguageEntity.getName());
        Assertions.assertEquals(createApplicationLanguageDTO.getDescription(), applicationLanguageEntity.getDescription());
    }

    @Test
    public void testCreateApplicationLanguageToEntityReturnNull() {

        //Resolve And condition to return null, but is not problema if use @Valid
        final ApplicationLanguageEntity applicationLanguageEntity = mapperApplicationLanguageEntity
                .toEntity(null, null);

        Assertions.assertNull(applicationLanguageEntity);

    }

    @Test
    public void testEntityToDto() {
        final ApplicationLanguageEntity applicationLanguageEntity = ApplicationLanguageEntity.builder()
                .idApplication(1)
                .idLanguage(1)
                .name("asd")
                .description("asd")
                .build();

        final ApplicationLanguageDTO applicationLanguageDTO = mapperApplicationLanguageEntity.toDto(applicationLanguageEntity);


        Assertions.assertNotNull(applicationLanguageEntity);
        Assertions.assertEquals(applicationLanguageEntity.getIdLanguage(), applicationLanguageDTO.getIdLanguage());
        Assertions.assertEquals(applicationLanguageEntity.getIdApplication(), applicationLanguageDTO.getIdApplication());
        Assertions.assertEquals(applicationLanguageEntity.getName(), applicationLanguageDTO.getName());
        Assertions.assertEquals(applicationLanguageEntity.getDescription(), applicationLanguageDTO.getDescription());
    }

    @Test
    public void testEntityToDtoReturnNull() {
        final ApplicationLanguageDTO applicationLanguageDTO = mapperApplicationLanguageEntity.toDto(null);
        Assertions.assertNull(applicationLanguageDTO);
    }
}
