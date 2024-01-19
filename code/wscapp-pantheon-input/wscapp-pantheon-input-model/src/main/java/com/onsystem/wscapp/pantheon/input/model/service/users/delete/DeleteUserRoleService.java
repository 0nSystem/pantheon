package com.onsystem.wscapp.pantheon.input.model.service.users.delete;

import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserRolesDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserRoleKeyEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users.MapperUserRoleEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete.IDeleteUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeleteUserRoleService implements IDeleteUserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private MapperUserRoleEntity mapperUserRoleEntity;

    @Override
    public void deleteRoles(Set<DeleteUserRolesDTO> deleteUserRoles) {
        //TODO applicationId?
        final List<UserRoleKeyEntity> userKeyRoleEntitiesToDelete = deleteUserRoles.stream()
                .flatMap(deleteUserRole -> mapperUserRoleEntity.deleteToEntity(deleteUserRole).stream())
                .toList();

        userRoleRepository.deleteAllById(userKeyRoleEntitiesToDelete);

    }
}
