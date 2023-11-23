package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.ArgumentsParams;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionKeyEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreatePermissionService;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
public class TestCreatePermissionService {
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
    public Stream<DynamicTest> createPermissionAddingRole() {
        final Collection<CreatePermissionDTO> createPermission = Set.of(MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK);
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

                    final RolePermissionEntity rolePermissionEntity = rolePermissionRepository.findById(RolePermissionKeyEntity.builder()
                                    .idPermission(permission.getIdPermission())
                                    .idRole(idRole).build())
                            .orElseThrow();
                    dynamicTests.add(
                            DynamicTest.dynamicTest(
                                    String.format("permission id: %s , role id: %s", rolePermissionEntity.getIdPermission(), rolePermissionEntity.getIdRole()),
                                    () -> ThrowingConsumerEntity.caseDefaultCorrectPermissionAddingRole(idRole, permission.getIdPermission()).accept(rolePermissionEntity)
                            )
                    );


                    return dynamicTests;
                }).flatMap(Collection::stream);
    }


    @ParameterizedTest
    @MethodSource({"com.onsystem.wscapp.pantheon.api.interfaces.ArgumentsParams#argumentsCreatePermissionLanguage"})
    public void createPermissionLanguage(final int permissionId, final int languageId, final CreatePermissionLanguageDTO createPermissionLanguage) throws Throwable {

        final PermissionLanguageDTO permissionLanguageInserted = iCreatePermissionService.createPermissionLanguages(
                        idPermission, Set.of(createPermissionLanguage)
                ).stream().findFirst()
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectCreatePermissionLanguageDTO(permissionId, languageId)
                .accept(permissionLanguageInserted);

    }

    /**
     * @return Argument (idPermission, idLanguage, PermissionLanguageDTO)
     */
    public Stream<Arguments> argumentsCreatePermissionLanguage() {
        return Stream.of(
                Arguments.of(idPermission, idLanguage,
                        MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER.idLanguage(idLanguage).build())
        );
    }

    @TestFactory
    public Stream<DynamicTest> createPermissionWithLanguagesBelongRoles() {
        final CreatePermissionDTO createPermission = MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK;
        final Set<CreatePermissionLanguageDTO> createPermissionLanguage = Set.of(MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
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
                            .idPermission(permissionWithLanguage.getPermission().getIdPermission())
                            .idRole(idRole).build())
                    .orElseThrow();
            dynamicTests.add(
                    DynamicTest.dynamicTest(
                            String.format("belong permission role  permission id: %s , role id: %s", rolePermissionEntity.getIdPermission(), rolePermissionEntity.getIdRole()),
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
