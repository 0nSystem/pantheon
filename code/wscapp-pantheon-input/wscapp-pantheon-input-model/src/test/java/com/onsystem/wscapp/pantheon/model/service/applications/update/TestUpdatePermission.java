package com.onsystem.wscapp.pantheon.model.service.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.UpdatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.DataInsertedBeforeTest;
import com.onsystem.wscapp.pantheon.input.api.interfaces.MockData;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreatePermissionService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update.IUpdatePermissionService;
import com.onsystem.wscapp.pantheon.model.service.applications.ThrowingConsumerDTO;
import jakarta.validation.constraints.NotNull;
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
class TestUpdatePermission {
    @Autowired
    private IUpdatePermissionService iUpdatePermissionService;
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


    private Stream<Arguments> argumentsUpdatePermission() {
        return Stream.of(
                Arguments.of(MockData.DataUpdateMockSchemeApplicationDTO.UPDATE_PERMISSION_MOCK_BUILDER
                        .idPermission(idPermission)
                        .build())
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsUpdatePermission")
    void updatePermission(final @NotNull UpdatePermissionDTO updatePermissionDTO) throws Throwable {

        iUpdatePermissionService.updatePermission(Set.of(updatePermissionDTO));

        final PermissionEntity permissionEntity = permissionRepository.findById(updatePermissionDTO.getIdPermission())
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdatePermission(updatePermissionDTO)
                .accept(permissionEntity);

    }


    private Stream<Arguments> argumentsUpdatePermissionLanguages() {
        return Stream.of(
                Arguments.of(MockData.DataUpdateMockSchemeApplicationDTO.UPDATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                        .idLanguage(idLanguage)
                        .idPermission(idPermission)
                        .build())
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsUpdatePermissionLanguages")
    void updatePermissionLanguages(final @NotNull UpdatePermissionLanguageDTO updatePermissionLanguageDTO) throws Throwable {
        final CreatePermissionLanguageDTO createPermissionLanguageDTO = MockData.DataCreateMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER
                .idLanguage(updatePermissionLanguageDTO.getIdLanguage())
                .build();

        iCreatePermissionService.createPermissionLanguages(updatePermissionLanguageDTO.getIdPermission(), Set.of(createPermissionLanguageDTO));

        iUpdatePermissionService.updatePermissionLanguages(Set.of(updatePermissionLanguageDTO));

        final PermissionLanguageEntity permissionLanguageEntity = permissionLanguageRepository.findById(
                        PermissionLanguageKeyEntity.builder()
                                .language(updatePermissionLanguageDTO.getIdLanguage())
                                .permission(updatePermissionLanguageDTO.getIdPermission())
                                .build())
                .orElseThrow();

        ThrowingConsumerDTO.caseDefaultCorrectUpdatePermissionLanguage(updatePermissionLanguageDTO)
                .accept(permissionLanguageEntity);

    }
}
