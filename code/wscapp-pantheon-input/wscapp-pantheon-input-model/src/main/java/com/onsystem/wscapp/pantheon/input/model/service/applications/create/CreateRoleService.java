package com.onsystem.wscapp.pantheon.input.model.service.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.*;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperRoleEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperRoleLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreatePermissionService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateRoleService;
import jakarta.validation.constraints.Positive;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateRoleService implements ICreateRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MapperRoleEntity mapperRoleEntity;
    @Autowired
    private RoleLanguageRepository roleLanguageRepository;
    @Autowired
    private MapperRoleLanguageEntity mapperRoleLanguageEntity;

    @Autowired
    private ICreatePermissionService iCreatePermissionService;


    @Override
    public Set<RoleDTO> createRole(int idApplication, Set<CreateRoleDTO> createRole) {
        final Set<RoleEntity> roleEntityMapped = createRole.stream()
                .map(role -> mapperRoleEntity.createToEntity(role, idApplication))
                .collect(Collectors.toSet());
        final List<RoleEntity> roleEntityInserted = roleRepository.saveAll(roleEntityMapped);

        return roleEntityInserted.stream()
                .map(roleEntity -> mapperRoleEntity.toDto(roleEntity))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles) {

        final Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> roleWithLanguagesAndPermissionWithLanguages = applicationRoles.stream()
                .map(role -> {
                    final RoleEntity roleEntityMapped = mapperRoleEntity.createToEntity(role.getRole(), idApplication);
                    final RoleEntity roleEntityInserted = roleRepository.save(roleEntityMapped);
                    final RoleDTO roleDTO = mapperRoleEntity.toDto(roleEntityInserted);

                    final var roleLanguages = CollectionUtils.isNotEmpty(role.getRoleLanguages())
                            ? createRoleLanguages(roleEntityInserted.getIdRole(), role.getRoleLanguages())
                            : null;

                    final var rolePermission = CollectionUtils.isNotEmpty(role.getRolePermission())
                            ? iCreatePermissionService.createPermissionWithLanguages(idApplication, roleDTO.getIdRole(), role.getRolePermission())
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
                .map(createLanguageRole -> mapperRoleLanguageEntity.createToEntity(createLanguageRole, idRole))
                .collect(Collectors.toSet());

        final List<RoleLanguageEntity> roleLanguageEntitiesInserted = roleLanguageRepository.saveAll(roleLanguageEntitiesMapped);

        return roleLanguageEntitiesInserted.stream()
                .map(roleLanguageEntity -> mapperRoleLanguageEntity.toDto(roleLanguageEntity))
                .collect(Collectors.toSet());
    }
}
