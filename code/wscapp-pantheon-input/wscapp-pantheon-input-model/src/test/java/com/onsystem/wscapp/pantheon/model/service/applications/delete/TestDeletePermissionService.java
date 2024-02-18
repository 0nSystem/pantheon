package com.onsystem.wscapp.pantheon.model.service.applications.delete;


import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreatePermissionService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.delete.IDeletePermissionService;
import jakarta.validation.constraints.Positive;
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
class TestDeletePermissionService {


    @Autowired
    private IDeletePermissionService iDeletePermissionService;
    @Autowired
    private ICreatePermissionService iCreatePermissionService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionLanguageRepository permissionLanguageRepository;

    @Autowired
    private Integer idPermission;
    @Autowired
    private Integer idLanguage;

    private Stream<Arguments> argumentsDeletePermission() {
        return Stream.of(
                Arguments.of(idPermission)
        );
    }

    @Rollback
    @ParameterizedTest
    @MethodSource("argumentsDeletePermission")
    void testDeletePermission(final @Positive int permissionId) {

        final long countBeforePermissionDelete = permissionRepository.count();

        iDeletePermissionService.deletePermission(List.of(permissionId));

        final long countAfterPermissionDelete = permissionRepository.count();

        Assertions.assertEquals(countBeforePermissionDelete - 1, countAfterPermissionDelete);

    }

    private Stream<Arguments> argumentsDeletePermissionLanguage() {
        return Stream.of(
                Arguments.of(idPermission, idLanguage)
        );
    }

    @Rollback
    @ParameterizedTest
    @MethodSource("argumentsDeletePermissionLanguage")
    void testDeletePermissionLanguage(final @Positive int permissionId, final @Positive int languageId) {
        final CreatePermissionLanguageDTO createPermissionLanguage = MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                .idLanguage(languageId)
                .build();

        iCreatePermissionService.createPermissionLanguages(permissionId, List.of(createPermissionLanguage));

        final long countBeforePermissionLanguageDelete = permissionLanguageRepository.count();

        iDeletePermissionService.deletePermissionLanguage(permissionId, List.of(languageId));

        final long countAfterPermissionLanguageDelete = permissionLanguageRepository.count();

        Assertions.assertEquals(countBeforePermissionLanguageDelete - 1, countAfterPermissionLanguageDelete);

    }
}
