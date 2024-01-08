package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserPermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.projections.UserBelongApplication;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserPermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserPermissionService;
import com.onsystem.wscapp.pantheon.model.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateUserPermissionService implements ICreateUserPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void assignPermissionInUser(Set<Integer> permissionIds, int userId) {
        validateUserBelongsApplicationToAssingPermissions(permissionIds, userId);
        validateUserNotContainsPermissionInDataBase(permissionIds, userId);

        final List<UserPermissionEntity> userPermissionEntities = permissionIds.stream()
                .map(permissionId -> UserPermissionEntity.builder()
                        .permission(PermissionEntity.builder().idPermission(permissionId).build())
                        .user(UserEntity.builder().idUser(userId).build())
                        .build())
                .toList();

        userPermissionRepository.saveAll(userPermissionEntities);
    }

    private void validateUserNotContainsPermissionInDataBase(Set<Integer> permissionIds, int userId) {
        final Set<PermissionEntity> permissionEntities = userPermissionRepository.findPermissionByIdPermissionInAndIdUser(permissionIds, userId);

        //TODO
        if (CollectionUtils.isNotEmpty(permissionEntities)) {
            throw new InfoException("");
        }
    }


    private void validateUserBelongsApplicationToAssingPermissions(Set<Integer> permissionIds, int userId) {
        final Set<Integer> applicationInPermission = permissionRepository.findAllById(permissionIds)
                .stream()
                .map(permissionEntity -> permissionEntity.getApplication().getIdApplication())
                .collect(Collectors.toSet());

        final Set<UserBelongApplication> applicationIdsInUser = userRoleRepository.findIdsApplicationByUser(Set.of(userId), Constants.AUTORIZED_ROLE_NAME);

        boolean notEqualsApplicationPermissionAndUserInApplication = false;

        if (notEqualsApplicationPermissionAndUserInApplication) {
            //TODO
            throw new InfoException("");
        }
    }
}
