package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.dto.application.*;
import com.onsystem.wscapp.pantheon.api.dto.attribute.*;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.CreatePermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.PermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.*;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.*;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.*;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateFullApplicationService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CreateFullApplicationService implements ICreateFullApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionLanguageRepository permissionLanguageRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleLanguageRepository roleLanguageRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public ApplicationFullInfoWithLanguagesDTO createFullApplication(final CreateFullApplicationDTO createApplication) {
        final ApplicationDTO applicationInserted = createApplication(createApplication.getApplication());
        return ApplicationFullInfoWithLanguagesDTO
                .builder()
                .application(applicationInserted)
                .applicationLanguages(createApplicationLanguages(applicationInserted.getIdApplication(), createApplication.getApplicationLanguages()))
                .applicationPermissions(createPermissionWithLanguages(applicationInserted.getIdApplication(), null, createApplication.getApplicationPermissions()))
                .applicationRoles(createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(applicationInserted.getIdApplication(), createApplication.getApplicationRoles()))
                .applicationAttributes(createAttributesWithLanguages(applicationInserted.getIdApplication(), createApplication.getApplicationAttributes()))
                .build();

    }

    private @NotNull ApplicationDTO createApplication(final CreateApplicationDTO createApplication) {
        final ApplicationEntity applicationEntityMapped = MapperApplicationEntity.mapperApplicationEntityFromCreateApplication()
                .apply(createApplication);

        final ApplicationEntity applicationInserted = applicationRepository.save(applicationEntityMapped);

        return MapperApplicationEntity.mapperApplicationDTOFromApplicationEntity()
                .apply(applicationInserted);
    }

    private @Nullable Set<ApplicationLanguageDTO> createApplicationLanguages(final @Positive int applicationId,
                                                                             final @Nullable Collection<CreateApplicationLanguageDTO> createApplicationLanguage) {

        if (createApplicationLanguage != null && !createApplicationLanguage.isEmpty()) {

            final Collection<ApplicationLanguageEntity> applicationLanguageEntitiesMapped = createApplicationLanguage
                    .stream()
                    .map(MapperApplicationLanguageEntity.mapperApplicationLanguageFromCreateApplicationLanguage(applicationId))
                    .collect(Collectors.toList());


            final List<ApplicationLanguageEntity> applicationLanguageEntitiesInserted = applicationLanguageRepository.saveAll(applicationLanguageEntitiesMapped);

            return applicationLanguageEntitiesInserted.stream()
                    .map(MapperApplicationLanguageEntity.mapperApplicationLanguageDTOFromApplicationEntity())
                    .collect(Collectors.toSet());
        }

        return null;
    }

    private @Nullable Set<PermissionWithLanguagesDTO> createPermissionWithLanguages(final int applicationId,
                                                                                    final @Nullable Integer idRole,
                                                                                    final Collection<CreatePermissionWithLanguagesDTO> createPermissionWithLanguages) {
        if (createPermissionWithLanguages != null && !createPermissionWithLanguages.isEmpty()) {
            final Set<PermissionWithLanguagesDTO> permissionEntitiesMapped = createPermissionWithLanguages.stream()
                    .map(cpl -> {
                        final PermissionEntity permissionEntityMapped = MapperPermissionEntity.mapperPermissionEntityFromCreatePermissionDTO(applicationId).apply(cpl.getPermission());
                        final PermissionEntity permissionInserted = permissionRepository.save(permissionEntityMapped);

                        return PermissionWithLanguagesDTO.builder()
                                .permission(MapperPermissionEntity.mapperPermissionDTOFromPermissionEntity().apply(permissionInserted))
                                .permissionLanguages(createPermissionLanguages(permissionInserted.getIdPermission(), cpl.getPermissionLanguages()))
                                .build();
                    })
                    .collect(Collectors.toSet());

            Optional.ofNullable(idRole).ifPresent(id -> {
                final Set<Integer> idsPermissionsInserted = permissionEntitiesMapped.stream()
                        .map(p -> p.getPermission().getIdPermission())
                        .collect(Collectors.toSet());
                createRelationsRoleWithPermission(id, idsPermissionsInserted);
            });

            return permissionEntitiesMapped;

        }
        return null;
    }

    private void createRelationsRoleWithPermission(final int idRole, final Collection<Integer> idsPermissionsInserted) {

        final Set<RolePermissionEntity> rolePermissionEntity = idsPermissionsInserted.stream()
                .map(idPermission -> RolePermissionEntity.builder()
                        .idPermission(idPermission)
                        .idRole(idRole)
                        .build())
                .collect(Collectors.toSet());

        rolePermissionRepository.saveAll(rolePermissionEntity);
    }

    private @Nullable Set<PermissionLanguageDTO> createPermissionLanguages(final int permissionId, final Collection<CreatePermissionLanguageDTO> createPermissionLanguages) {
        if (createPermissionLanguages != null && !createPermissionLanguages.isEmpty()) {
            final Set<PermissionLanguageEntity> permissionLanguagesEntitiesMapped = createPermissionLanguages
                    .stream()
                    .map(MapperPermissionLanguageEntity.mapperPermissionLanguageEntityFromCreatePermissionLanguageDTO(permissionId))
                    .collect(Collectors.toSet());

            final List<PermissionLanguageEntity> permissionLanguageEntitiesInserted = permissionLanguageRepository.saveAll(permissionLanguagesEntitiesMapped);

            return permissionLanguageEntitiesInserted.stream()
                    .map(MapperPermissionLanguageEntity.mapperPermissionLanguageDTOFromLanguageEntity())
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private @Nullable Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles) {

        if (applicationRoles != null && !applicationRoles.isEmpty()) {
            final Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> roleWithLanguagesAndPermissionWithLanguages = applicationRoles.stream()
                    .map(role -> {
                        final RoleEntity roleEntityMapped = MapperRoleEntity.mapperRoleEntityFromCreateRoleDTO(idApplication).apply(role.getRole());
                        final RoleEntity roleEntityInserted = roleRepository.save(roleEntityMapped);
                        final RoleDTO roleDTO = MapperRoleEntity.mapperRoleDTOFromRoleEntity().apply(roleEntityInserted);

                        return RoleWithLanguagesAndPermissionWithLanguagesDTO.builder()
                                .role(roleDTO)
                                .roleLanguages(createRoleLanguages(roleEntityInserted.getIdRole(), role.getRoleLanguage()))
                                .permissions(createPermissionWithLanguages(idApplication, roleDTO.getIdRole(), role.getRolePermission()))
                                .build();
                    })
                    .collect(Collectors.toSet());

            return roleWithLanguagesAndPermissionWithLanguages;
        }

        return null;
    }

    private @Nullable Set<RoleLanguageDTO> createRoleLanguages(final int idRole, final Collection<CreateRoleLanguageDTO> createRoleLanguages) {

        if (createRoleLanguages != null && !createRoleLanguages.isEmpty()) {
            final Set<RoleLanguageEntity> roleLanguageEntitiesMapped = createRoleLanguages.stream()
                    .map(MapperRoleLanguageEntity.mapperRoleLanguageEntityFromCreateRoleLanguage(idRole))
                    .collect(Collectors.toSet());

            final List<RoleLanguageEntity> roleLanguageEntitiesInserted = roleLanguageRepository.saveAll(roleLanguageEntitiesMapped);

            return roleLanguageEntitiesInserted.stream()
                    .map(MapperRoleLanguageEntity.mapperRoleLanguageDTOFromRoleLanguageEntity())
                    .collect(Collectors.toSet());
        }

        return null;
    }

    private @Nullable Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final int applicationId, final Collection<CreateAttributeDTO> createAttribute) {

        if (createAttribute != null && !createAttribute.isEmpty()) {

            final Set<AttributeWithLanguagesDTO> attributeWithLanguages = createAttribute.stream()
                    .map(attribute -> {
                        final AttributeEntity attributeEntityMapped = MapperAttributeEntity.mapperAttributeEntityFromCreateAttributeDTO(applicationId)
                                .apply(attribute);
                        final AttributeEntity attributeInsert = attributeRepository.save(attributeEntityMapped);
                        final AttributeDTO attributeDTO = MapperAttributeEntity.mapperAttributeDTOFromAttribute()
                                .apply(attributeInsert);

                        return AttributeWithLanguagesDTO.builder()
                                .attribute(attributeDTO)
                                .attributeLanguages(createAttributesLanguages(attributeInsert.getIdAttribute(), attribute.getAttributeLanguages()))
                                .build();
                    })
                    .collect(Collectors.toSet());

            return attributeWithLanguages;
        }

        return null;
    }


    private @Nullable Set<AttributeLanguageDTO> createAttributesLanguages(final int attributeId, final Collection<CreateAttributeLanguageDTO> createAttribute) {

        if (createAttribute != null && !createAttribute.isEmpty()) {

            final Set<AttributeLanguageEntity> attributeLanguageEntitiesMapped = createAttribute.stream()
                    .map(MapperAttributeLanguageEntity.mapperAttributeLanguageEntityFromCreateAttributeLanguageDTO(attributeId))
                    .collect(Collectors.toSet());

            final List<AttributeLanguageEntity> attributeLanguageEntitiesInsert = attributeLanguageRepository.saveAll(attributeLanguageEntitiesMapped);

            return attributeLanguageEntitiesInsert.stream()
                    .map(MapperAttributeLanguageEntity.mapperAttributeLanguageDTOFromAttributeEntity())
                    .collect(Collectors.toSet());

        }

        return null;
    }

}
