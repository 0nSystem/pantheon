package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperPermissionEntityTest {

    @Autowired
    private MapperPermissionEntity mapperPermissionEntity;

    @Test
    public void testCreateToEntity() {
        final Integer applicationId = 1;

        final CreatePermissionDTO createPermissionDTO = CreatePermissionDTO.builder()
                .name("asd")
                .description("asd")
                .build();

        final PermissionEntity permissionEntity = mapperPermissionEntity.createToEntity(createPermissionDTO, applicationId);


        Assertions.assertNotNull(permissionEntity);
        Assertions.assertEquals(applicationId, permissionEntity.getApplication().getIdApplication());
        Assertions.assertEquals(createPermissionDTO.getName(), permissionEntity.getName());
        Assertions.assertEquals(createPermissionDTO.getDescription(), permissionEntity.getDescription());

    }

    @Test
    public void testCreateToEntityReturnNull() {
        final PermissionEntity permissionEntity = mapperPermissionEntity.createToEntity(null, null);
        Assertions.assertNull(permissionEntity);
    }

    @Test
    public void testEntityToDto() {

        final PermissionEntity permissionEntity = PermissionEntity.builder()
                .application(ApplicationEntity.builder().idApplication(1).build())
                .idPermission(1)
                .name("asd")
                .description("asd")
                .build();

        final PermissionDTO permissionDTO = mapperPermissionEntity.toDto(permissionEntity);


        Assertions.assertNotNull(permissionDTO);
        Assertions.assertEquals(permissionEntity.getIdPermission(), permissionDTO.getIdPermission());
        Assertions.assertEquals(permissionEntity.getApplication().getIdApplication(), permissionDTO.getIdApplication());
        Assertions.assertEquals(permissionEntity.getName(), permissionDTO.getName());
        Assertions.assertEquals(permissionEntity.getDescription(), permissionDTO.getDescription());

    }

    @Test
    public void testEntityToDtoReturnNull() {
        final PermissionDTO permissionDTO = mapperPermissionEntity.toDto(null);
        Assertions.assertNull(permissionDTO);
    }

}
