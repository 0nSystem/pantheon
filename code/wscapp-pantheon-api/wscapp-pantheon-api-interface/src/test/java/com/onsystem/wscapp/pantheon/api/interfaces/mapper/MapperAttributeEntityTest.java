package com.onsystem.wscapp.pantheon.api.interfaces.mapper;


import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperAttributeEntityTest {

    @Autowired
    private MapperAttributeEntity mapperAttributeEntity;


    @Test
    public void testToEntity() {
        final Integer applicationId = 1;

        final CreateAttributeDTO createAttributeDTO = CreateAttributeDTO.builder()
                .name("asd")
                .description("asd")
                .build();

        final AttributeEntity attributeEntity = mapperAttributeEntity.createToEntity(createAttributeDTO, applicationId);

        Assertions.assertNotNull(attributeEntity);
        Assertions.assertEquals(applicationId, attributeEntity.getApplication().getIdApplication());
        Assertions.assertEquals(createAttributeDTO.getName(), attributeEntity.getName());
        Assertions.assertEquals(createAttributeDTO.getDescription(), attributeEntity.getDescription());

    }

    @Test
    public void testToEntityReturnNull() {
        final AttributeEntity attributeEntity = mapperAttributeEntity.createToEntity(null, null);
        Assertions.assertNull(attributeEntity);
    }

    @Test
    public void testToDto() {
        final AttributeEntity attributeEntity = AttributeEntity.builder()
                .idAttribute(1)
                .application(ApplicationEntity.builder().idApplication(1).build())
                .name("asd")
                .description("asd")
                .build();

        final AttributeDTO attributeDTO = mapperAttributeEntity.toDto(attributeEntity);

        Assertions.assertNotNull(attributeDTO);
        Assertions.assertEquals(attributeEntity.getIdAttribute(), attributeDTO.getIdAttribute());
        Assertions.assertEquals(attributeEntity.getIdAttribute(), attributeDTO.getIdApplication());
        Assertions.assertEquals(attributeEntity.getName(), attributeDTO.getName());
        Assertions.assertEquals(attributeEntity.getDescription(), attributeDTO.getDescription());

    }

    @Test
    public void testToDtoReturnNull() {
        final AttributeDTO attributeDTO = mapperAttributeEntity.toDto(null);
        Assertions.assertNull(attributeDTO);

    }


}
