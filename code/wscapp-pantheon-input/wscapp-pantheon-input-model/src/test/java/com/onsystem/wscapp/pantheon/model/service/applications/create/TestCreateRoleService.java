package com.onsystem.wscapp.pantheon.model.service.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.CreateRoleDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.CreateRoleWithLanguagesAndPermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.RoleDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.RoleWithLanguagesAndPermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RolePermissionKeyEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateRoleService;
import com.onsystem.wscapp.pantheon.model.service.applications.ThrowingConsumerDTO;
import com.onsystem.wscapp.pantheon.model.service.applications.ThrowingConsumerEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Transactional
class TestCreateRoleService {

    @Autowired
    private ICreateRoleService createRoleService;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private Integer idApplication;

    @Autowired
    private Integer idRole;
    @Autowired
    private Integer idLanguage;

    @TestFactory
    Stream<DynamicTest> createRole() {
        final CreateRoleDTO createRole = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ROLE_MOCK;

        final Set<RoleDTO> rolesInserted = createRoleService.createRole(idApplication, Set.of(createRole));

        return DynamicTest.stream(rolesInserted.stream(),
                roleDTO -> String.format("idRole : %s", roleDTO.getIdRole()),
                ThrowingConsumerDTO.caseDefaultCorrectCreateRole(idApplication)
        );
    }

    @TestFactory
    Stream<DynamicTest> createRoleLanguages() {
        final var createRoleLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_ROLE_LANGUAGE_MOCK
                .idLanguage(idLanguage)
                .build();

        final var roleLanguagesDtoInserted = createRoleService.createRoleLanguages(idRole, Set.of(createRoleLanguage));


        return DynamicTest.stream(roleLanguagesDtoInserted.stream(),
                roleLanguageDTO -> String.format("RoleId : %s, LanguageId: %s", roleLanguageDTO.getIdRole(), roleLanguageDTO.getIdLanguage()),
                ThrowingConsumerDTO.caseDefaultCorrectCreateRoleLanguage(idRole, idLanguage)
        );
    }

    @TestFactory
    Stream<DynamicTest> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage() {


        final CreateRoleWithLanguagesAndPermissionWithLanguagesDTO createRoleWithLanguagesAndPermissionWithLanguagesDTO = CreateRoleWithLanguagesAndPermissionWithLanguagesDTO.builder()
                .role(MockData.DataCreateMockSchemeApplicationDTO.CREATE_ROLE_MOCK)
                .roleLanguages(
                        Set.of(MockData.DataCreateMockSchemeApplicationDTO.CREATE_ROLE_LANGUAGE_MOCK.idLanguage(idLanguage)
                                .build())
                ).rolePermission(
                        Set.of(
                                CreatePermissionWithLanguagesDTO.builder()
                                        .permission(MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK)
                                        .permissionLanguages(Set.of(MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                                                .idLanguage(idLanguage).build()))
                                        .build()
                        )
                )
                .build();

        Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> roleWithLanguagesAndPermissionWithLanguagesDTOS = createRoleService.createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(idApplication, Set.of(createRoleWithLanguagesAndPermissionWithLanguagesDTO));

        return roleWithLanguagesAndPermissionWithLanguagesDTOS.stream()
                .map(roleWithLanguagesAndPermissionWithLanguages -> {
                    final List<DynamicTest> dynamicTests = new ArrayList<>();

                    dynamicTests.add(
                            DynamicTest.dynamicTest(String.format("create role id: %s", roleWithLanguagesAndPermissionWithLanguages.getRole()),
                                    () -> ThrowingConsumerDTO.caseDefaultCorrectCreateRole(idApplication)
                                            .accept(roleWithLanguagesAndPermissionWithLanguages.getRole()))
                    );

                    dynamicTests.addAll(
                            DynamicTest.stream(roleWithLanguagesAndPermissionWithLanguages.getRoleLanguages().stream(),
                                    roleLanguageDTO -> String.format("Role Language RoleId : %s, LanguageId: %s", roleLanguageDTO.getIdRole(), roleLanguageDTO.getIdLanguage()),
                                    ThrowingConsumerDTO.caseDefaultCorrectCreateRoleLanguage(roleWithLanguagesAndPermissionWithLanguages.getRole().getIdRole(), idLanguage)
                            ).toList()
                    );

                    dynamicTests.addAll(
                            roleWithLanguagesAndPermissionWithLanguages.getPermissions().stream()
                                    .map(permissionsLanguages -> {
                                        final List<DynamicTest> dynamicTestsPermissions = new ArrayList<>();
                                        dynamicTestsPermissions.add(
                                                DynamicTest.dynamicTest(
                                                        String.format("Permission id: %s , application id: %s ", permissionsLanguages.getPermission().getIdPermission(), permissionsLanguages.getPermission().getIdApplication()),
                                                        () -> ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionDTO(idApplication).accept(permissionsLanguages.getPermission())
                                                )
                                        );

                                        final RolePermissionEntity rolePermissionEntity = rolePermissionRepository.findById(RolePermissionKeyEntity.builder()
                                                        .permission(permissionsLanguages.getPermission().getIdPermission())
                                                        .role(roleWithLanguagesAndPermissionWithLanguages.getRole().getIdRole()).build())

                                                .orElseThrow();
                                        dynamicTests.add(
                                                DynamicTest.dynamicTest(
                                                        String.format("belong permission role permission id: %s , role id: %s", rolePermissionEntity.getPermission().getIdPermission(), rolePermissionEntity.getRole().getIdRole()),
                                                        () -> ThrowingConsumerEntity.caseDefaultCorrectPermissionAddingRole(
                                                                roleWithLanguagesAndPermissionWithLanguages.getRole().getIdRole(), permissionsLanguages.getPermission().getIdPermission()
                                                        ).accept(rolePermissionEntity)
                                                )
                                        );

                                        dynamicTestsPermissions.addAll(
                                                DynamicTest.stream(permissionsLanguages.getPermissionLanguages().stream(),
                                                                permissionLanguage -> String.format("Permission Language idPermission : %s, idLanguage: %s", permissionLanguage.getIdPermission(), permissionLanguage.getIdLanguage()),
                                                                ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionLanguageDTO(permissionsLanguages.getPermission().getIdPermission(), idLanguage))
                                                        .toList()
                                        );

                                        return dynamicTestsPermissions;
                                    })
                                    .flatMap(Collection::stream)
                                    .toList()
                    );


                    return dynamicTests;
                })
                .flatMap(Collection::stream);
    }


}
