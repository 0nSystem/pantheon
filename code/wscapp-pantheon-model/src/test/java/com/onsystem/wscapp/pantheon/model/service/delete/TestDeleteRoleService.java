package com.onsystem.wscapp.pantheon.model.service.delete;


import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateRoleService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.delete.IDeleteRoleService;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Rollback
class TestDeleteRoleService {

    @Autowired
    private IDeleteRoleService iDeleteRoleService;
    @Autowired
    private ICreateRoleService iCreateRoleService;

}
