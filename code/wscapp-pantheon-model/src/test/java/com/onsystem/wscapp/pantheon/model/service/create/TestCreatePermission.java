package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.LanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreatePermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCreatePermission {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private LanguageRepository languageRepository;


    @Autowired
    private ICreatePermissionService iCreatePermissionService;



    @Test
    public void createPermission() {


    }


}
