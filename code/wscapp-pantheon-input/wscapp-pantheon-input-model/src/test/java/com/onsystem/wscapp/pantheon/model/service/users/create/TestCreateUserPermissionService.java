package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserPermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserPermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
class TestCreateUserPermissionService {

    @Autowired
    private ICreateUserPermissionService iCreateUserPermissionService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idPermission;
    @Autowired
    private Integer idUser;


    @Test
    void testCorrectAssingUserPermission() {
        //Validation require authorize role not work because not implemented

        iCreateUserPermissionService.assignPermissionInUser(Set.of(idPermission), idUser);

        final Set<PermissionEntity> userRolesInApplication = userPermissionRepository.findPermissionEntitiesByIdUserAndIdApplication(idUser, idApplication);

        Assertions.assertFalse(userRolesInApplication.isEmpty());


    }


}
