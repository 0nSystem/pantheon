package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications;


import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.commons.entity.spublic.LanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperAttributeLanguageEntityTest {

    @Autowired
    private MapperAttributeLanguageEntity mapperAttributeLanguageEntity;


    @Test
    public void testCreateToEntity() {
        final Integer attributeId = 1;

        final CreateAttributeLanguageDTO createAttributeLanguageDTO = CreateAttributeLanguageDTO.builder()
                .idLanguage(1)
                .name("asd")
                .description("asd")
                .build();

        final AttributeLanguageEntity attributeLanguageEntity = mapperAttributeLanguageEntity.createToEntity(createAttributeLanguageDTO, attributeId);


        Assertions.assertNotNull(attributeLanguageEntity);
        Assertions.assertEquals(attributeId, attributeLanguageEntity.getAttribute().getIdAttribute());
        Assertions.assertEquals(createAttributeLanguageDTO.getIdLanguage(), attributeLanguageEntity.getLanguage().getIdLanguage());
        Assertions.assertEquals(createAttributeLanguageDTO.getName(), attributeLanguageEntity.getName());
        Assertions.assertEquals(createAttributeLanguageDTO.getDescription(), attributeLanguageEntity.getDescription());

    }

    @Test
    public void testCreateToEntityReturnNull() {
        final AttributeLanguageEntity attributeLanguageEntity = mapperAttributeLanguageEntity.createToEntity(null, null);
        Assertions.assertNull(attributeLanguageEntity);
    }

    @Test
    public void testEntityToDto() {

        final AttributeLanguageEntity attributeLanguageEntity = AttributeLanguageEntity.builder()
                .attribute(AttributeEntity.builder().idAttribute(1).build())
                .language(LanguageEntity.builder().idLanguage(1).build())
                .name("asd")
                .description("asd")
                .build();

        final AttributeLanguageDTO attributeLanguageDTO = mapperAttributeLanguageEntity.toDto(attributeLanguageEntity);


        Assertions.assertNotNull(attributeLanguageDTO);
        Assertions.assertEquals(attributeLanguageEntity.getLanguage().getIdLanguage(), attributeLanguageDTO.getIdAttribute());
        Assertions.assertEquals(attributeLanguageEntity.getLanguage().getIdLanguage(), attributeLanguageDTO.getIdLanguage());
        Assertions.assertEquals(attributeLanguageEntity.getName(), attributeLanguageDTO.getName());
        Assertions.assertEquals(attributeLanguageEntity.getDescription(), attributeLanguageDTO.getDescription());

    }

    @Test
    public void testEntityToDtoReturnNull() {
        final AttributeLanguageDTO attributeLanguageDTO = mapperAttributeLanguageEntity.toDto(null);
        Assertions.assertNull(attributeLanguageDTO);
    }


}
