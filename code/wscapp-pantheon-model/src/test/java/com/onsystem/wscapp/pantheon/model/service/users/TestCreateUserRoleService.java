package com.onsystem.wscapp.pantheon.model.service.users;

import com.onsystem.wscapp.pantheon.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleKeyEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserRoleService;
import com.onsystem.wscapp.pantheon.model.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.onsystem.wscapp.pantheon.api.interfaces.MockData.DataCreateMockSchemeApplicationDTO.CREATE_APPLICATION_MOCK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
class TestCreateUserRoleService {

    @Autowired
    private ICreateUserRoleService iCreateUserRoleService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ICreateApplicationService iCreateApplicationService;

    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idApplication;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    void testAssingRoleToUserCorrect() {
        final ApplicationDTO application = iCreateApplicationService.createApplication(CREATE_APPLICATION_MOCK);

        final ApplicationEntity applicationEntity = applicationRepository
                .findById(application.getIdApplication())
                .orElseThrow();

        final Set<Integer> rolesIdsApplication = applicationEntity.getRoles().stream()
                .map(RoleEntity::getIdRole)
                .collect(Collectors.toSet());

        iCreateUserRoleService.assignRole(rolesIdsApplication, idUser);


    }


}
