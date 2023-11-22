package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreatePermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({DataInsertedBeforeTest.class})
public class TestCreatePermission {
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

    //TODO create test to assing role to permission
    @TestFactory
    public Stream<DynamicTest> createPermission(TestInfo testInfo) {
        final Collection<CreatePermissionDTO> createPermission = Set.of(MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK);
        final Set<PermissionDTO> permissionInserted = iCreatePermissionService.createPermission(idApplication, null, createPermission);


        return DynamicTest.stream(permissionInserted.stream(),
                (permission) -> String.format("%s - Execute test permission name: %s , description: %s", testInfo.getDisplayName(), permission.getName(), permission.getDescription()),
                (permission) -> {
                    Assertions.assertNotNull(permission);
                    Assertions.assertTrue(permission.getIdPermission() > 0);
                    Assertions.assertEquals(idApplication, permission.getIdApplication());
                    Assertions.assertNotNull(permission.getName());
                    Assertions.assertNotNull(permission.getDescription());
                });
    }


    @TestFactory
    public Stream<DynamicTest> createPermissionLanguage(TestInfo testInfo) {
        final Collection<CreatePermissionLanguageDTO> createPermissionLanguage = Set.of(MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                .idLanguage(idLanguage)
                .build());
        final Set<PermissionLanguageDTO> permissionLanguageInserted = iCreatePermissionService.createPermissionLanguages(idPermission, createPermissionLanguage);

        return DynamicTest.stream(permissionLanguageInserted.stream(),
                (permission) -> String.format("%s - Execute test language permission name: %s , description: %s", testInfo.getDisplayName(), permission.getName(), permission.getDescription()),
                (permission) -> {
                    Assertions.assertNotNull(permission);
                    Assertions.assertEquals(idPermission, permission.getIdPermission());
                    Assertions.assertEquals(idLanguage, permission.getIdLanguage());
                    Assertions.assertNotNull(permission.getName());
                    Assertions.assertNotNull(permission.getDescription());
                });
    }

    @TestFactory
    public Stream<DynamicTest> createPermissionWithLanguages(TestInfo testInfo) {
        final CreatePermissionDTO createPermission = MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_MOCK;
        final Set<CreatePermissionLanguageDTO> createPermissionLanguage = Set.of(MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                .idLanguage(idLanguage)
                .build());

        final CreatePermissionWithLanguagesDTO createPermissionWithLanguages = CreatePermissionWithLanguagesDTO.builder()
                .permission(createPermission)
                .permissionLanguages(createPermissionLanguage)
                .build();

        final Set<PermissionWithLanguagesDTO> permissionWithLanguagesInserted = iCreatePermissionService.createPermissionWithLanguages(idApplication, null, Set.of(createPermissionWithLanguages));

        final Stream<DynamicTest> testCreatedPermission = DynamicTest.stream(permissionWithLanguagesInserted.stream(),
                (permission) -> String.format("%s - Execute test permission id: %s, name: %s , description: %s", testInfo.getDisplayName(), permission.getPermission().getIdPermission(), permission.getPermission().getName(), permission.getPermission().getDescription()),
                (permission) -> {
                    Assertions.assertNotNull(permission);
                    Assertions.assertTrue(permission.getPermission().getIdPermission() > 0);
                    Assertions.assertNotNull(permission.getPermission().getName());
                    Assertions.assertNotNull(permission.getPermission().getDescription());
                });


        final Stream<PermissionLanguageDTO> allPermissionLanguageInserted = permissionWithLanguagesInserted.stream().map(PermissionWithLanguagesDTO::getPermissionLanguages).flatMap(Collection::stream);
        final Stream<DynamicTest> testCreatedPermissionLanguage = DynamicTest.stream(
                allPermissionLanguageInserted,
                permissionLanguage -> String.format("%s - Execute test language permission id: %s, name: %s , description: %s", testInfo.getDisplayName(), permissionLanguage.getIdPermission(), permissionLanguage.getName(), permissionLanguage.getDescription()),
                permissionLanguage -> {
                    Assertions.assertNotNull(permissionLanguage);
                    Assertions.assertTrue(permissionLanguage.getIdPermission() > 0);
                    Assertions.assertTrue(permissionLanguage.getIdLanguage() > 0);
                    Assertions.assertNotNull(permissionLanguage.getName());
                    Assertions.assertNotNull(permissionLanguage.getDescription());
                });

        return Stream.concat(testCreatedPermission, testCreatedPermissionLanguage);
    }


}
