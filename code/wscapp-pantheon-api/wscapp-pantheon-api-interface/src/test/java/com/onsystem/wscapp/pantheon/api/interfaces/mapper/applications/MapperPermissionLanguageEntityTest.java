package com.onsystem.wscapp.pantheon.api.interfaces.mapper.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionLanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperPermissionLanguageEntityTest {

    @Autowired
    private MapperPermissionLanguageEntity mapperPermissionLanguageEntity;


    @Test
    public void testCreateToEntity() {
        final Integer permissionId = 1;

        final CreatePermissionLanguageDTO createPermissionLanguageDTO = CreatePermissionLanguageDTO.builder()
                .name("asd")
                .description("asd")
                .build();

        final PermissionLanguageEntity permissionLanguageEntity = mapperPermissionLanguageEntity.createToEntity(createPermissionLanguageDTO, permissionId);


        Assertions.assertNotNull(permissionLanguageEntity);
        Assertions.assertEquals(permissionId, permissionLanguageEntity.getPermission().getIdPermission());
        Assertions.assertEquals(createPermissionLanguageDTO.getName(), permissionLanguageEntity.getName());
        Assertions.assertEquals(createPermissionLanguageDTO.getDescription(), permissionLanguageEntity.getDescription());

    }

    @Test
    public void testCreateToEntityReturnNull() {
        final PermissionLanguageEntity permissionLanguageEntity = mapperPermissionLanguageEntity.createToEntity(null, null);
        Assertions.assertNull(permissionLanguageEntity);
    }

    @Test
    public void testEntityToDto() {

        final PermissionLanguageEntity permissionLanguageEntity = PermissionLanguageEntity.builder()
                .permission(PermissionEntity.builder().idPermission(1).build())
                .name("asd")
                .description("asd")
                .build();

        final PermissionLanguageDTO permissionLanguageDTO = mapperPermissionLanguageEntity.toDto(permissionLanguageEntity);


        Assertions.assertNotNull(permissionLanguageDTO);
        Assertions.assertEquals(permissionLanguageEntity.getPermission().getIdPermission(), permissionLanguageDTO.getIdPermission());
        Assertions.assertEquals(permissionLanguageEntity.getName(), permissionLanguageDTO.getName());
        Assertions.assertEquals(permissionLanguageEntity.getDescription(), permissionLanguageDTO.getDescription());

    }

    @Test
    public void testEntityToDtoReturnNull() {
        final PermissionLanguageDTO permissionLanguageDTO = mapperPermissionLanguageEntity.toDto(null);
        Assertions.assertNull(permissionLanguageDTO);
    }

}
