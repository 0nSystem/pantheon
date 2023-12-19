package com.onsystem.wscapp.pantheon.model.service.users;

import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Import({DataInsertedBeforeTest.class})
public class TestCreateUserRoleService {

    @Autowired
    private ICreateUserRoleService iCreateUserRoleService;

    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idRole;

    @Test
    void testAssingRoleToUser() {

        //iCreateUserRoleService.assignRole();

    }


}
