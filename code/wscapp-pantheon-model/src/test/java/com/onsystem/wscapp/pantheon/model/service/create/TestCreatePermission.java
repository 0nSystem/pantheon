package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.LanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreatePermissionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCreatePermission {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private LanguageRepository languageRepository;


    @Autowired
    private ICreatePermissionService iCreatePermissionService;

    private static Integer idApplication;
    private static Integer idLanguage;

    @BeforeAll
    public static void setUp(@Autowired ApplicationRepository applicationRepository, @Autowired LanguageRepository languageRepository) {

    }


    @Test
    public void createPermission() {




    }


}
