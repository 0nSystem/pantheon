package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;


    @Test
    void testQueryNotContainsSyntaxErrors(){
        applicationRepository.findPermissionInfoProjectionByIdApplicationIn(1, List.of(1));
        applicationRepository.findRoleInfoProjectionByIdApplicationIn(1, List.of(1));
        applicationRepository.findAttributeInfoProjectionByIdApplicationIn(1, List.of(1));

    }



}
