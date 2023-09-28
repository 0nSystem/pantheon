package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserPermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.UserPermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.UserRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserPermissionService implements IUserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    public void insert(Collection<Integer> idsPermissions, Collection<Integer> idsUsers) {
        final List<UserPermissionEntity> permissionEntities = idsPermissions.stream().flatMap(idPermission ->
                idsUsers.stream().map(idUser -> UserPermissionEntity.builder().idPermission(idPermission).idUser(idUser).build())
        ).toList();

        userPermissionRepository.saveAll(permissionEntities);
    }

    @Override
    public void delete(Collection<Integer> idsPermissions, Collection<Integer> idsUsers) {
        final List<UserPermissionEntity> permissionEntities = idsPermissions.stream().flatMap(idPermission ->
                idsUsers.stream().map(idUser -> UserPermissionEntity.builder().idPermission(idPermission).idUser(idUser).build())
        ).toList();

        userPermissionRepository.deleteAll(permissionEntities);
    }
}
