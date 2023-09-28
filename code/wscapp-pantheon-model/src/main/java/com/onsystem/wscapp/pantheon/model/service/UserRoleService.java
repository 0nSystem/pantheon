package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.UserRoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void insert(final Collection<Integer> idsRoles, final Collection<Integer> idsUsers) {
        //TODO el usuario esta en la aplicacion
        //TODO Ver si los tiene?

        final List<UserRoleEntity> userRoleEntities = idsRoles.stream()
                .flatMap(idRole -> idsUsers.stream()
                        .map(idUser -> UserRoleEntity.builder().idUser(idUser).idRole(idRole).build()))
                .toList();

        userRoleRepository.saveAll(userRoleEntities);
    }

    @Override
    public void delete(final Collection<Integer> idsRoles, final Collection<Integer> idsUsers) {
        //TODO el usuario esta en la aplicacion
        //TODO Ver si los tiene?

        final List<UserRoleEntity> userRoleEntities = idsRoles.stream()
                .flatMap(idRole -> idsUsers.stream()
                        .map(idUser -> UserRoleEntity.builder().idUser(idUser).idRole(idRole).build()))
                .toList();
        userRoleRepository.deleteAll(userRoleEntities);
    }
}
