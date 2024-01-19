package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;


import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.spublic.LanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperApplicationLanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MapperApplicationLanguageEntityTest {


    @Autowired
    private MapperApplicationLanguageEntity mapperApplicationLanguageEntity;

    private static ApplicationEntity APPLICATION_ENTITY = ApplicationEntity.builder()
            .idApplication(1)
            .build();

    @Test
    public void testCreateApplicationLanguageToEntity() {
        final CreateApplicationLanguageDTO createApplicationLanguageDTO = CreateApplicationLanguageDTO.builder()
                .name("asd")
                .description("asd")
                .idLanguage(1)
                .build();

        final ApplicationLanguageEntity applicationLanguageEntity = mapperApplicationLanguageEntity.createToEntity(createApplicationLanguageDTO, APPLICATION_ENTITY.getIdApplication());

        Assertions.assertNotNull(applicationLanguageEntity);
        Assertions.assertEquals(createApplicationLanguageDTO.getIdLanguage(), applicationLanguageEntity.getLanguage().getIdLanguage());
        Assertions.assertEquals(APPLICATION_ENTITY.getIdApplication(), applicationLanguageEntity.getApplication().getIdApplication());
        Assertions.assertEquals(createApplicationLanguageDTO.getName(), applicationLanguageEntity.getName());
        Assertions.assertEquals(createApplicationLanguageDTO.getDescription(), applicationLanguageEntity.getDescription());
    }

    @Test
    public void testCreateApplicationLanguageToEntityReturnNull() {

        //Resolve And condition to return null, but is not problema if use @Valid
        final ApplicationLanguageEntity applicationLanguageEntity = mapperApplicationLanguageEntity
                .createToEntity(null, null);

        Assertions.assertNull(applicationLanguageEntity);

    }

    @Test
    public void testEntityToDto() {
        final ApplicationLanguageEntity applicationLanguageEntity = ApplicationLanguageEntity.builder()
                .application(ApplicationEntity.builder().idApplication(1).build())
                .language(LanguageEntity.builder().idLanguage(1).build())
                .name("asd")
                .description("asd")
                .build();

        final ApplicationLanguageDTO applicationLanguageDTO = mapperApplicationLanguageEntity.toDto(applicationLanguageEntity);


        Assertions.assertNotNull(applicationLanguageEntity);
        Assertions.assertEquals(applicationLanguageEntity.getLanguage().getIdLanguage(), applicationLanguageDTO.getIdLanguage());
        Assertions.assertEquals(applicationLanguageEntity.getApplication().getIdApplication(), applicationLanguageDTO.getIdApplication());
        Assertions.assertEquals(applicationLanguageEntity.getName(), applicationLanguageDTO.getName());
        Assertions.assertEquals(applicationLanguageEntity.getDescription(), applicationLanguageDTO.getDescription());
    }

    @Test
    public void testEntityToDtoReturnNull() {
        final ApplicationLanguageDTO applicationLanguageDTO = mapperApplicationLanguageEntity.toDto(null);
        Assertions.assertNull(applicationLanguageDTO);
    }
}
