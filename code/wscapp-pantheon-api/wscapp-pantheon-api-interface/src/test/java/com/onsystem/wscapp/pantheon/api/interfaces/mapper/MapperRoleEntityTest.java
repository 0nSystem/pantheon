package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperRoleEntityTest {

    @Autowired
    private MapperRoleEntity mapperRoleEntity;


    @Test
    public void testCreateToEntity() {
        final Integer applicationId = 1;

        final CreateRoleDTO createRoleDTO = CreateRoleDTO.builder()
                .name("asd")
                .description("asd")
                .build();

        final RoleEntity roleEntity = mapperRoleEntity.createToEntity(createRoleDTO, applicationId);


        Assertions.assertNotNull(roleEntity);
        Assertions.assertEquals(applicationId, roleEntity.getApplication().getIdApplication());
        Assertions.assertEquals(createRoleDTO.getName(), roleEntity.getName());
        Assertions.assertEquals(createRoleDTO.getDescription(), roleEntity.getDescription());

    }

    @Test
    public void testCreateToEntityReturnNull() {
        final RoleEntity permissionEntity = mapperRoleEntity.createToEntity(null, null);
        Assertions.assertNull(permissionEntity);
    }

    @Test
    public void testEntityToDto() {

        final RoleEntity roleEntity = RoleEntity.builder()
                .application(ApplicationEntity.builder().idApplication(1).build())
                .idRole(1)
                .name("asd")
                .description("asd")
                .build();

        final RoleDTO roleDTO = mapperRoleEntity.toDto(roleEntity);


        Assertions.assertNotNull(roleDTO);
        Assertions.assertEquals(roleEntity.getIdRole(), roleDTO.getIdRole());
        Assertions.assertEquals(roleEntity.getApplication().getIdApplication(), roleDTO.getIdApplication());
        Assertions.assertEquals(roleEntity.getName(), roleDTO.getName());
        Assertions.assertEquals(roleEntity.getDescription(), roleDTO.getDescription());

    }

    @Test
    public void testEntityToDtoReturnNull() {
        final RoleDTO roleDTO = mapperRoleEntity.toDto(null);
        Assertions.assertNull(roleDTO);
    }

}
