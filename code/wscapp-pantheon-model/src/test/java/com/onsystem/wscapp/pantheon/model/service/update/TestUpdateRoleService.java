package com.onsystem.wscapp.pantheon.model.service.update;


import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreateRoleService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdateRoleService;
import com.onsystem.wscapp.pantheon.model.service.ThrowingConsumerDTO;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
class TestUpdateRoleService {

    @Autowired
    private IUpdateRoleService iUpdateRoleService;
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

    private Stream<Arguments> argumentsUpdateRole() {
        return Stream.of(
                Arguments.of(
                        MockData.DataUpdateMockSchemeApplicationDTO.UPDATE_ROLE_MOCK_BUILDER
                                .idRole(idRole).build()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsUpdateRole")
    void updateRole(final UpdateRoleDTO updateRoleDTO) throws Throwable {
        iUpdateRoleService.updateRoles(Set.of(updateRoleDTO));

        final RoleEntity roleEntity = roleRepository.findById(updateRoleDTO.getIdRole())
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdateRole(updateRoleDTO)
                .accept(roleEntity);

    }

    private Stream<Arguments> argumentsUpdateRoleLanguage() {
        return Stream.of(
                Arguments.of(
                        MockData.DataUpdateMockSchemeApplicationDTO.UPDATE_ROLE_LANGUAGE_MOCK_BUILDER
                                .idLanguage(idLanguage)
                                .idRole(idRole)
                                .build()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsUpdateRoleLanguage")
    void updateRoleLanguage(final UpdateRoleLanguageDTO updateRoleLanguage) throws Throwable {
        final CreateRoleLanguageDTO createRoleLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ROLE_LANGUAGE_MOCK
                .idLanguage(updateRoleLanguage.getIdLanguage())
                .build();

        iCreateRoleService.createRoleLanguages(updateRoleLanguage.getIdRole(), Set.of(createRoleLanguage));
        iUpdateRoleService.updateRoleLanguages(Set.of(updateRoleLanguage));

        final RoleLanguageEntity roleLanguageEntity = roleLanguageRepository.findById(
                RoleLanguageKeyEntity.builder()
                        .language(updateRoleLanguage.getIdLanguage())
                        .role(updateRoleLanguage.getIdRole())
                        .build()
        ).orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdateRoleLanguage(updateRoleLanguage)
                .accept(roleLanguageEntity);

    }

}
