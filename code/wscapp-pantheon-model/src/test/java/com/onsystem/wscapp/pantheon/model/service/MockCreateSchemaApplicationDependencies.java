package com.onsystem.wscapp.pantheon.model.service;


import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.helpers.TimeHelper;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockCreateSchemaApplicationDependencies {

    @InjectMocks
    private CreateSchemaApplicationDependencies createSchemaApplicationDependencies;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationLanguageRepository applicationLanguageRepository;
    @Mock
    private PermissionRepository permissionRepository;
    @Mock
    private PermissionLanguageRepository permissionLanguageRepository;

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleLanguageRepository roleLanguageRepository;
    @Mock
    private AttributeRepository attributeRepository;
    @Mock
    private AttributeLanguageRepository attributeLanguageRepository;
    @Mock
    private RolePermissionRepository rolePermissionRepository;

    @Mock
    private MapperApplicationEntity mapperApplicationEntity;

    @Mock
    private TimeHelper timeHelper;


    @Test
    public void createApplication() {
        final var modelToCreateApplication = CreateApplicationDTO.builder()
                .name("name")
                .description("description")
                .highIdUser(1)
                .build();

        final var applicationCreateDTO = createSchemaApplicationDependencies.createApplication(modelToCreateApplication);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(applicationCreateDTO);
            Assertions.assertTrue(applicationCreateDTO.getIdApplication() > 0);

            Assertions.assertEquals(modelToCreateApplication.getName(), applicationCreateDTO.getName());
            Assertions.assertEquals(modelToCreateApplication.getDescription(), applicationCreateDTO.getDescription());

            Assertions.assertNotNull(applicationCreateDTO.getDeleteIdUser());
            Assertions.assertNotNull(applicationCreateDTO.getDeleteDate());

            Assertions.assertEquals(modelToCreateApplication.getHighIdUser(), applicationCreateDTO.getHighIdUser());

        });

    }
}
