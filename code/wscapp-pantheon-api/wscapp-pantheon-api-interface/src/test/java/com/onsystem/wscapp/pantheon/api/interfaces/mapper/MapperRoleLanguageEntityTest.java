package com.onsystem.wscapp.pantheon.api.interfaces.mapper;

import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperRoleLanguageEntityTest {
    @Autowired
    private MapperRoleLanguageEntity mapperRoleLanguageEntity;


    @Test
    public void testCreateToEntity() {
        final Integer roleId = 1;

        final CreateRoleLanguageDTO createRoleLanguageDTO = CreateRoleLanguageDTO.builder()
                .idLanguage(1)
                .name("asd")
                .description("asd")
                .build();

        final RoleLanguageEntity roleLanguageEntity = mapperRoleLanguageEntity.createToEntity(createRoleLanguageDTO, roleId);


        Assertions.assertNotNull(roleLanguageEntity);
        Assertions.assertEquals(roleId, roleLanguageEntity.getRole().getIdRole());
        Assertions.assertEquals(createRoleLanguageDTO.getIdLanguage(), roleLanguageEntity.getLanguage().getIdLanguage());
        Assertions.assertEquals(createRoleLanguageDTO.getName(), roleLanguageEntity.getName());
        Assertions.assertEquals(createRoleLanguageDTO.getDescription(), roleLanguageEntity.getDescription());

    }

    @Test
    public void testCreateToEntityReturnNull() {
        final RoleLanguageEntity permissionEntity = mapperRoleLanguageEntity.createToEntity(null, null);
        Assertions.assertNull(permissionEntity);
    }

    @Test
    public void testEntityToDto() {

        final RoleLanguageEntity roleEntity = RoleLanguageEntity.builder()
                .language(LanguageEntity.builder().idLanguage(1).build())
                .role(RoleEntity.builder().idRole(1).build())
                .name("asd")
                .description("asd")
                .build();

        final RoleLanguageDTO roleLanguageDTO = mapperRoleLanguageEntity.toDto(roleEntity);


        Assertions.assertNotNull(roleLanguageDTO);
        Assertions.assertEquals(roleEntity.getRole().getIdRole(), roleLanguageDTO.getIdRole());
        Assertions.assertEquals(roleEntity.getLanguage().getIdLanguage(), roleLanguageDTO.getIdLanguage());
        Assertions.assertEquals(roleEntity.getName(), roleLanguageDTO.getName());
        Assertions.assertEquals(roleEntity.getDescription(), roleLanguageDTO.getDescription());

    }

    @Test
    public void testEntityToDtoReturnNull() {
        final RoleLanguageDTO roleLanguageDTO = mapperRoleLanguageEntity.toDto(null);
        Assertions.assertNull(roleLanguageDTO);
    }
}
