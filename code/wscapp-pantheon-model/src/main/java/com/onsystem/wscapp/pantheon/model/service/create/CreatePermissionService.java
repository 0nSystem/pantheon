package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.dto.permission.*;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperPermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperPermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.create.ICreatePermissionService;
import jakarta.validation.constraints.Positive;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreatePermissionService implements ICreatePermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private MapperPermissionEntity mapperPermissionEntity;

    @Autowired
    private PermissionLanguageRepository permissionLanguageRepository;
    @Autowired
    private MapperPermissionLanguageEntity mapperPermissionLanguageEntity;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;


    @Override
    public Set<PermissionWithLanguagesDTO> createPermissionWithLanguages(final int applicationId,
                                                                         final @Nullable Integer idRole,
                                                                         final Collection<CreatePermissionWithLanguagesDTO> createPermissionWithLanguages) {
        final Set<PermissionWithLanguagesDTO> permissionEntitiesMapped = createPermissionWithLanguages.stream()
                .map(cpl -> {
                    final PermissionEntity permissionEntityMapped = mapperPermissionEntity.createToEntity(cpl.getPermission(), applicationId);
                    final PermissionEntity permissionInserted = permissionRepository.save(permissionEntityMapped);


                    return PermissionWithLanguagesDTO.builder()
                            .permission(mapperPermissionEntity.toDto(permissionInserted))
                            .permissionLanguages(CollectionUtils.isNotEmpty(cpl.getPermissionLanguages())
                                    ? createPermissionLanguages(permissionInserted.getIdPermission(), cpl.getPermissionLanguages())
                                    : null)
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
    public Set<PermissionDTO> createPermission(int applicationId, Integer roleId, Collection<CreatePermissionDTO> createPermission) {
        final var permissionEntityMapped = createPermission.stream()
                .map(createPermissionDTO -> mapperPermissionEntity.createToEntity(createPermissionDTO, applicationId))
                .collect(Collectors.toSet());

        final var permissionInserted = permissionRepository.saveAll(permissionEntityMapped);

        Optional.ofNullable(roleId).ifPresent(id -> {
            final List<Integer> idsPermissionInserted = permissionInserted.stream().map(PermissionEntity::getIdPermission).toList();
            createRelationsRoleWithPermission(id, idsPermissionInserted);
        });

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
                .map(createPermissionLanguage -> mapperPermissionLanguageEntity.createToEntity(createPermissionLanguage, permissionId))
                .collect(Collectors.toSet());

        final List<PermissionLanguageEntity> permissionLanguageEntitiesInserted = permissionLanguageRepository.saveAll(permissionLanguagesEntitiesMapped);

        return permissionLanguageEntitiesInserted.stream()
                .map(permissionEntity -> mapperPermissionLanguageEntity.toDto(permissionEntity))
                .collect(Collectors.toSet());
    }
}
