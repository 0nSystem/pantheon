package com.onsystem.wscapp.pantheon.model.service.applications.create;

import com.onsystem.wscapp.pantheon.api.dto.applications.permission.*;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RolePermissionKeyEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreatePermissionService;
import com.onsystem.wscapp.pantheon.model.service.applications.ThrowingConsumerDTO;
import com.onsystem.wscapp.pantheon.model.service.applications.ThrowingConsumerEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
@Transactional
class TestCreatePermissionService {
    @Autowired
    private Integer idApplication;
    @Autowired
    private Integer idLanguage;
    @Autowired
    private Integer idPermission;
    @Autowired
    private Integer idRole;


    @Autowired
    private ICreatePermissionService iCreatePermissionService;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;


    @TestFactory
    Stream<DynamicTest> createPermissionAddingRole() {
        final Collection<CreatePermissionDTO> createPermission = Set.of(MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK);
        final Set<PermissionDTO> permissionInserted = iCreatePermissionService.createPermission(idApplication, idRole, createPermission);

        return permissionInserted.stream()
                .map(permission -> {
                    final List<DynamicTest> dynamicTests = new ArrayList<>();

                    dynamicTests.add(
                            DynamicTest.dynamicTest(
                                    String.format("permission id: %s , application id: %s", permission.getIdPermission(), permission.getIdApplication()),
                                    () -> ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionDTO(idApplication).accept(permission)
                            )
                    );

                    final RolePermissionEntity rolePermissionEntity = rolePermissionRepository.findById(
                                    RolePermissionKeyEntity.builder()
                                            .permission(permission.getIdPermission())
                                            .role(idRole).build())
                            .orElseThrow();
                    dynamicTests.add(
                            DynamicTest.dynamicTest(
                                    String.format("permission id: %s , role id: %s", rolePermissionEntity.getPermission().getIdPermission(), rolePermissionEntity.getRole().getIdRole()),
                                    () -> ThrowingConsumerEntity.caseDefaultCorrectPermissionAddingRole(idRole, permission.getIdPermission()).accept(rolePermissionEntity)
                            )
                    );


                    return dynamicTests;
                }).flatMap(Collection::stream);
    }


    private Stream<Arguments> argumentsCreatePermissionLanguage() {
        return Stream.of(
                Arguments.of(idPermission,
                        MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                                .idLanguage(idLanguage).build())
        );
    }

    @ParameterizedTest
    @MethodSource({"argumentsCreatePermissionLanguage"})
    void createPermissionLanguage(final int permissionId, final CreatePermissionLanguageDTO createPermissionLanguage) throws Throwable {

        final PermissionLanguageDTO permissionLanguageInserted = iCreatePermissionService.createPermissionLanguages(
                        permissionId, Set.of(createPermissionLanguage)
                ).stream().findFirst()
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionLanguageDTO(permissionId, createPermissionLanguage.getIdLanguage())
                .accept(permissionLanguageInserted);

    }


    @TestFactory
    Stream<DynamicTest> createPermissionWithLanguagesBelongRoles() {
        final CreatePermissionDTO createPermission = MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK;
        final Set<CreatePermissionLanguageDTO> createPermissionLanguage = Set.of(MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                .idLanguage(idLanguage)
                .build());

        final CreatePermissionWithLanguagesDTO createPermissionWithLanguages = CreatePermissionWithLanguagesDTO.builder()
                .permission(createPermission)
                .permissionLanguages(createPermissionLanguage)
                .build();

        final Set<PermissionWithLanguagesDTO> permissionWithLanguagesInserted = iCreatePermissionService.createPermissionWithLanguages(idApplication, idRole, Set.of(createPermissionWithLanguages));

        final Stream<DynamicTest> testCreationPermissionWithLanguages = permissionWithLanguagesInserted.stream().map(permissionWithLanguage -> {
            final List<DynamicTest> dynamicTests = new ArrayList<>();

            //Test default create permission
            dynamicTests.add(
                    DynamicTest.dynamicTest(
                            String.format("permission id: %s , application id: %s", permissionWithLanguage.getPermission().getIdPermission(), permissionWithLanguage.getPermission().getIdApplication()),
                            () -> ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionDTO(idApplication).accept(permissionWithLanguage.getPermission())
                    )
            );


            final RolePermissionEntity rolePermissionEntity = rolePermissionRepository.findById(RolePermissionKeyEntity.builder()
                            .permission(permissionWithLanguage.getPermission().getIdPermission())
                            .role(idRole).build())
                    .orElseThrow();
            dynamicTests.add(
                    DynamicTest.dynamicTest(
                            String.format("belong permission role  permission id: %s , role id: %s", rolePermissionEntity.getPermission().getIdPermission(), rolePermissionEntity.getRole().getIdRole()),
                            () -> ThrowingConsumerEntity.caseDefaultCorrectPermissionAddingRole(
                                    idRole, permissionWithLanguage.getPermission().getIdPermission()
                            ).accept(rolePermissionEntity)
                    )
            );

            //Test default create language permission
            dynamicTests.addAll(
                    DynamicTest.stream(
                                    permissionWithLanguage.getPermissionLanguages().stream(),
                                    permissionLanguage -> String.format("test language permission id: %s, name: %s , description: %s", permissionLanguage.getIdPermission(), permissionLanguage.getName(), permissionLanguage.getDescription()),
                                    permissionLanguage -> ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionLanguageDTO(permissionLanguage.getIdPermission(), idLanguage).accept(permissionLanguage))
                            .toList()
            );

            return dynamicTests;
        }).flatMap(Collection::stream);

        return testCreationPermissionWithLanguages;
    }


}
