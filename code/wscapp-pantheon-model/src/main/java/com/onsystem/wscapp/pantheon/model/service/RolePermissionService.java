package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionId;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RolePermissionService implements IRolePermissionService {

    @Autowired
    private RolePermissionRepository permissionRepository;

    @Override
    public void insert(Collection<Integer> idsRoles, Collection<Integer> idsPermissions) {
        final List<RolePermissionEntity> rolePermissionEntities = idsRoles.stream()
                .flatMap(idRole -> idsPermissions.stream().map(idPermission -> RolePermissionEntity.builder()
                        .idPermission(idPermission)
                        .idRole(idRole)
                        .build()))
                .toList();
        permissionRepository.saveAll(rolePermissionEntities);
    }

    @Override
    public void delete(Collection<Integer> idsRoles, Collection<Integer> idsPermissions) {
        final List<RolePermissionId> rolePermissionEntities = idsRoles.stream()
                .flatMap(idRole -> idsPermissions.stream().map(idPermission -> RolePermissionId.builder()
                        .idPermission(idPermission)
                        .idRole(idRole)
                        .build()))
                .toList();
        permissionRepository.deleteAllByIdInBatch(rolePermissionEntities);
    }
}
