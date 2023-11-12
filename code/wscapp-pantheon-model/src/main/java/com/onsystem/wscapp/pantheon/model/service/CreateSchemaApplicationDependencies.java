package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.dto.application.*;
import com.onsystem.wscapp.pantheon.api.dto.attribute.*;
import com.onsystem.wscapp.pantheon.api.dto.permission.*;
import com.onsystem.wscapp.pantheon.api.dto.role.*;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.*;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.*;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateSchemaApplicationDependencies;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CreateSchemaApplicationDependencies implements ICreateSchemaApplicationDependencies {

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

    @Autowired
    private MapperApplicationEntity mapperApplicationEntity;
    @Autowired
    private MapperApplicationLanguageEntity mapperApplicationLanguageEntity;
    @Autowired
    private MapperAttributeEntity mapperAttributeEntity;
    @Autowired
    private MapperAttributeLanguageEntity mapperAttributeLanguageEntity;
    @Autowired
    private MapperPermissionEntity mapperPermissionEntity;
    @Autowired
    private MapperPermissionLanguageEntity mapperPermissionLanguageEntity;


    @Override
    public ApplicationFullInfoWithLanguagesDTO createFullApplication(final CreateFullApplicationDTO createApplication) {
        final var application = createApplication(createApplication.getApplication());

        final var applicationLanguages = CollectionUtils.isNotEmpty(createApplication.getApplicationLanguages())
                ? createApplicationLanguages(application.getIdApplication(), createApplication.getApplicationLanguages())
                : null;

        final var applicationPermission = CollectionUtils.isNotEmpty(createApplication.getApplicationPermissions())
                ? createPermissionWithLanguages(application.getIdApplication(), null, createApplication.getApplicationPermissions())
                : null;

        final var applicationRoles = CollectionUtils.isNotEmpty(createApplication.getApplicationRoles())
                ? createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(application.getIdApplication(), createApplication.getApplicationRoles())
                : null;

        final var applicationAttributes = CollectionUtils.isNotEmpty(createApplication.getApplicationAttributes())
                ? createAttributesWithLanguages(application.getIdApplication(), createApplication.getApplicationAttributes())
                : null;

        return ApplicationFullInfoWithLanguagesDTO
                .builder()
                .application(application)
                .applicationLanguages(applicationLanguages)
                .applicationPermissions(applicationPermission)
                .applicationRoles(applicationRoles)
                .applicationAttributes(applicationAttributes)
                .build();

    }

    @Override
    public @NotNull ApplicationDTO createApplication(final CreateApplicationDTO createApplication) {
        final ApplicationEntity applicationEntityMapped = mapperApplicationEntity.createEntityToEntity(createApplication);

        final ApplicationEntity applicationInserted = applicationRepository.save(applicationEntityMapped);

        return mapperApplicationEntity.entityToDTO(applicationInserted);
    }

    public Set<ApplicationLanguageDTO> createApplicationLanguages(final @Positive int applicationId,
                                                                  final Collection<CreateApplicationLanguageDTO> createApplicationLanguage) {

        final Collection<ApplicationLanguageEntity> applicationLanguageEntitiesMapped = createApplicationLanguage
                .stream()
                .map(modelApplicationLanguage -> mapperApplicationLanguageEntity.toEntity(modelApplicationLanguage, applicationId))
                .collect(Collectors.toList());


        final List<ApplicationLanguageEntity> applicationLanguageEntitiesInserted = applicationLanguageRepository.saveAll(applicationLanguageEntitiesMapped);

        return applicationLanguageEntitiesInserted.stream()
                .map(applicationLanguagesInserted -> mapperApplicationLanguageEntity.toDto(applicationLanguagesInserted))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PermissionWithLanguagesDTO> createPermissionWithLanguages(final int applicationId,
                                                                         final @Nullable Integer idRole,
                                                                         final Collection<CreatePermissionWithLanguagesDTO> createPermissionWithLanguages) {
        final Set<PermissionWithLanguagesDTO> permissionEntitiesMapped = createPermissionWithLanguages.stream()
                .map(cpl -> {
                    final PermissionEntity permissionEntityMapped = mapperPermissionEntity.toEntity(cpl.getPermission(), applicationId);
                    final PermissionEntity permissionInserted = permissionRepository.save(permissionEntityMapped);

                    return PermissionWithLanguagesDTO.builder()
                            .permission(mapperPermissionEntity.toDto(permissionInserted))
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

    @Override
    public Set<PermissionDTO> createPermission(int applicationId, Collection<CreatePermissionDTO> createPermission) {
        final var permissionEntityMapped = createPermission.stream()
                .map(createPermissionDTO -> mapperPermissionEntity.toEntity(createPermissionDTO, applicationId))
                .collect(Collectors.toSet());

        final var permissionInserted = permissionRepository.saveAll(permissionEntityMapped);

        return permissionInserted.stream()
                .map(permissionEntity -> mapperPermissionEntity.toDto(permissionEntity))
                .collect(Collectors.toSet());
    }

    @Override
    public void createRelationsRoleWithPermission(final @Positive int idRole, final Collection<Integer> idsPermissionsInserted) {

        final Set<RolePermissionEntity> rolePermissionEntity = idsPermissionsInserted.stream()
                .map(idPermission -> RolePermissionEntity.builder()
                        .idPermission(idPermission)
                        .idRole(idRole)
                        .build())
                .collect(Collectors.toSet());

        rolePermissionRepository.saveAll(rolePermissionEntity);
    }

    @Override
    public @Nullable Set<PermissionLanguageDTO> createPermissionLanguages(final @Positive int permissionId, final Collection<CreatePermissionLanguageDTO> createPermissionLanguages) {
        final Set<PermissionLanguageEntity> permissionLanguagesEntitiesMapped = createPermissionLanguages
                .stream()
                .map(createPermissionLanguage -> mapperPermissionLanguageEntity.toEntity(createPermissionLanguage, permissionId))
                .collect(Collectors.toSet());

        final List<PermissionLanguageEntity> permissionLanguageEntitiesInserted = permissionLanguageRepository.saveAll(permissionLanguagesEntitiesMapped);

        return permissionLanguageEntitiesInserted.stream()
                .map(permissionEntity -> mapperPermissionLanguageEntity.toDto(permissionEntity))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles) {

        final Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> roleWithLanguagesAndPermissionWithLanguages = applicationRoles.stream()
                .map(role -> {
                    final RoleEntity roleEntityMapped = MapperRoleEntity.toEntity(idApplication).apply(role.getRole());
                    final RoleEntity roleEntityInserted = roleRepository.save(roleEntityMapped);
                    final RoleDTO roleDTO = MapperRoleEntity.toDto().apply(roleEntityInserted);

                    final var roleLanguages = CollectionUtils.isNotEmpty(role.getRoleLanguage())
                            ? createRoleLanguages(roleEntityInserted.getIdRole(), role.getRoleLanguage())
                            : null;

                    final var rolePermission = CollectionUtils.isNotEmpty(role.getRolePermission())
                            ? createPermissionWithLanguages(idApplication, roleDTO.getIdRole(), role.getRolePermission())
                            : null;

                    return RoleWithLanguagesAndPermissionWithLanguagesDTO.builder()
                            .role(roleDTO)
                            .roleLanguages(roleLanguages)
                            .permissions(rolePermission)
                            .build();
                })
                .collect(Collectors.toSet());

        return roleWithLanguagesAndPermissionWithLanguages;
    }

    @Override
    public Set<RoleLanguageDTO> createRoleLanguages(final @Positive int idRole, final Collection<CreateRoleLanguageDTO> createRoleLanguages) {

        final Set<RoleLanguageEntity> roleLanguageEntitiesMapped = createRoleLanguages.stream()
                .map(MapperRoleLanguageEntity.toEntity(idRole))
                .collect(Collectors.toSet());

        final List<RoleLanguageEntity> roleLanguageEntitiesInserted = roleLanguageRepository.saveAll(roleLanguageEntitiesMapped);

        return roleLanguageEntitiesInserted.stream()
                .map(MapperRoleLanguageEntity.toDto())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final @Positive int applicationId, final Collection<CreateAttributeDTO> createAttribute) {

        final Set<AttributeWithLanguagesDTO> attributeWithLanguages = createAttribute.stream()
                .map(attribute -> {
                    final AttributeEntity attributeEntityMapped = mapperAttributeEntity.toEntity(attribute, applicationId);
                    final AttributeEntity attributeInsert = attributeRepository.save(attributeEntityMapped);
                    final AttributeDTO attributeDTO = mapperAttributeEntity.toDto(attributeInsert);

                    return AttributeWithLanguagesDTO.builder()
                            .attribute(attributeDTO)
                            .attributeLanguages(createAttributesLanguages(attributeInsert.getIdAttribute(), attribute.getAttributeLanguages()))
                            .build();
                })
                .collect(Collectors.toSet());

        return attributeWithLanguages;
    }

    @Override
    public Set<AttributeLanguageDTO> createAttributesLanguages(final @Positive int attributeId, final Collection<CreateAttributeLanguageDTO> createAttribute) {

        final Set<AttributeLanguageEntity> attributeLanguageEntitiesMapped = createAttribute.stream()
                .map(createAttributeLanguageDto -> mapperAttributeLanguageEntity.toEntity(createAttributeLanguageDto, attributeId))
                .collect(Collectors.toSet());

        final List<AttributeLanguageEntity> attributeLanguageEntitiesInsert = attributeLanguageRepository.saveAll(attributeLanguageEntitiesMapped);

        return attributeLanguageEntitiesInsert.stream()
                .map(attributeLanguageEntity -> mapperAttributeLanguageEntity.toDto(attributeLanguageEntity))
                .collect(Collectors.toSet());
    }

}
