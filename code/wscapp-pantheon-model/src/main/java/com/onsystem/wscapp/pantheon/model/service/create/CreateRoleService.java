package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.dto.role.*;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperRoleLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreatePermissionService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateRoleService;
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
    public Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(
            @Positive int idApplication,
            Set<CreateRoleWithLanguagesAndPermissionWithLanguagesDTO> applicationRoles) {

        final Set<RoleWithLanguagesAndPermissionWithLanguagesDTO> roleWithLanguagesAndPermissionWithLanguages = applicationRoles.stream()
                .map(role -> {
                    final RoleEntity roleEntityMapped = mapperRoleEntity.toEntity(role.getRole(), idApplication);
                    final RoleEntity roleEntityInserted = roleRepository.save(roleEntityMapped);
                    final RoleDTO roleDTO = mapperRoleEntity.toDto(roleEntityInserted);

                    final var roleLanguages = CollectionUtils.isNotEmpty(role.getRoleLanguage())
                            ? createRoleLanguages(roleEntityInserted.getIdRole(), role.getRoleLanguage())
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
                .map(createLanguageRole -> mapperRoleLanguageEntity.toEntity(createLanguageRole, idRole))
                .collect(Collectors.toSet());

        final List<RoleLanguageEntity> roleLanguageEntitiesInserted = roleLanguageRepository.saveAll(roleLanguageEntitiesMapped);

        return roleLanguageEntitiesInserted.stream()
                .map(roleLanguageEntity -> mapperRoleLanguageEntity.toDto(roleLanguageEntity))
                .collect(Collectors.toSet());
    }
}
