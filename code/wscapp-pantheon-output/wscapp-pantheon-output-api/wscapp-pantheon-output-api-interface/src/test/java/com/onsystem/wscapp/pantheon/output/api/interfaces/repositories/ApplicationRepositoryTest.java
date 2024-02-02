package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;


import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.ApplicationInfoProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;



    @Test
    void testQueryNotContainsSyntaxErrors() {
        applicationRepository.findPermissionInfoProjectionByIdApplicationIn(1, List.of(1));
        applicationRepository.findRoleInfoProjectionByIdApplicationIn(1, List.of(1));
        applicationRepository.findAttributeInfoProjectionByIdApplicationIn(1, List.of(1));

    }


}
