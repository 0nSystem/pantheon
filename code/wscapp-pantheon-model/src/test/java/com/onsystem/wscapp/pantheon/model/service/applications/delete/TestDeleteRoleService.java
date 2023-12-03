package com.onsystem.wscapp.pantheon.model.service.applications.delete;


import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateRoleService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.delete.IDeleteRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Transactional
class TestDeleteRoleService {

    @Autowired
    private IDeleteRoleService iDeleteRoleService;
    @Autowired
    private ICreateRoleService iCreateRoleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleLanguageRepository roleLanguageRepository;

    @Autowired
    private Integer idRole;
    @Autowired
    private Integer idLanguage;


    private Stream<Arguments> argumentsDeleteRole() {
        return Stream.of(
                Arguments.of(idRole)
        );
    }

    @MethodSource("argumentsDeleteRole")
    @ParameterizedTest
    @Rollback
    void deleteRole(final int idRole) {


        final long countBeforeRoleDelete = roleRepository.count();

        iDeleteRoleService.deleteRole(List.of(idRole));

        final long countAfterRoleDelete = roleRepository.count();

        Assertions.assertEquals(countBeforeRoleDelete - 1, countAfterRoleDelete);

    }


    private Stream<Arguments> argumentsDeleteRoleLanguage() {
        return Stream.of(
                Arguments.of(idRole, idLanguage)
        );
    }

    @MethodSource("argumentsDeleteRoleLanguage")
    @ParameterizedTest
    @Rollback
    void deleteRoleLanguage(final int idRole, final Integer idLanguage) {

        iCreateRoleService.createRoleLanguages(idRole,
                List.of(MockData.DataCreateMockSchemeApplicationDTO.CREATE_ROLE_LANGUAGE_MOCK
                        .idLanguage(idLanguage)
                        .build())
        );

        final long countBeforeRoleDelete = roleLanguageRepository.count();

        iDeleteRoleService.deleteRoleLanguage(idRole, List.of(idLanguage));

        final long countAfterRoleDelete = roleLanguageRepository.count();

        Assertions.assertEquals(countBeforeRoleDelete - 1, countAfterRoleDelete);

    }

}
