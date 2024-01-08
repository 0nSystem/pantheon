package com.onsystem.wscapp.pantheon.model.service.users.delete;

import com.onsystem.wscapp.pantheon.api.dto.users.DeleteUserRolesDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.delete.IDeleteUserRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class TestDeleteUserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private IDeleteUserRoleService iDeleteUserRoleService;
    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idRole;

    @Test
    @Rollback
    void testCorrectCaseDeleteUserRole() {
        final UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .role(RoleEntity.builder().idRole(idRole).build())
                .user(UserEntity.builder().idUser(idUser).build())
                .build();

        userRoleRepository.save(userRoleEntity);


        final long countBeforeDeleteUserRole = userRoleRepository.count();

        final DeleteUserRolesDTO deleteUserRolesDTO = DeleteUserRolesDTO.builder()
                .roleIds(Set.of(idRole))
                .userId(idUser)
                .build();

        iDeleteUserRoleService.deleteRoles(Set.of(deleteUserRolesDTO));

        final long countAfterDeleteUserRole = userRoleRepository.count();

        Assertions.assertEquals(countBeforeDeleteUserRole - 1, countAfterDeleteUserRole);

    }
}
