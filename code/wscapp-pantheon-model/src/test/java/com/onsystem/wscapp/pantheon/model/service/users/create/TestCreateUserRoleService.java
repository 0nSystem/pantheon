package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.Collectors;

import static com.onsystem.wscapp.pantheon.api.interfaces.MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
class TestCreateUserRoleService {

    @Autowired
    private ICreateUserRoleService iCreateUserRoleService;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Integer idUser;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    @Rollback
    void testAssingRoleToUserCorrect() {
        final ApplicationDTO application = iCreateApplicationService.createApplication(CREATE_APPLICATION_MOCK);

        final Set<Integer> rolesInApplication = roleRepository.findByApplicationIdApplication(application.getIdApplication()).stream()
                .map(RoleEntity::getIdRole)
                .collect(Collectors.toSet());

        //Validation require authorize not work because not implemented

        iCreateUserRoleService.assignRole(rolesInApplication, idUser);

        final Set<RoleEntity> userRolesInApplication = userRoleRepository.findRoleEntitiesByIdUserAndIdApplication(idUser, application.getIdApplication());

        Assertions.assertFalse(userRolesInApplication.isEmpty());

    }


}
