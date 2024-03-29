package com.onsystem.wscapp.pantheon.input.model.service.users.create;

import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.input.api.interfaces.projections.UserBelongApplication;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserRoleService;
import com.onsystem.wscapp.pantheon.input.model.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CreateUserRoleService implements ICreateUserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void assignRole(Set<Integer> roleIds, int userId) {
        validateUserBelongsApplicationToAssingRoles(roleIds, userId);
        validateUserNotContainsRolesInDataBase(roleIds, userId);

        //TODO move to mapper?
        final List<UserRoleEntity> userRoleEntities = roleIds.stream()
                .map(roleId -> UserRoleEntity.builder()
                        .role(RoleEntity.builder().idRole(roleId).build())
                        .user(UserEntity.builder().idUser(userId).build())
                        .build())
                .toList();

        userRoleRepository.saveAll(userRoleEntities);

    }

    private void validateUserNotContainsRolesInDataBase(Set<Integer> roleIds, int userId) {
        final Set<RoleEntity> roleAssignedInUser = userRoleRepository.findByUserRoleEntitiesByIdRoleInAndIdUser(roleIds, userId);

        if (CollectionUtils.isNotEmpty(roleAssignedInUser)) {
            //FIXME send roles assigned to client
            throw new InfoException("");
        }

    }


    private void validateUserBelongsApplicationToAssingRoles(final Set<Integer> roleIds, final int userId) {
        final Set<Integer> applicationIdsInRoles = roleRepository.findIdsApplicationByIdRoleIn(roleIds);


        final Set<UserBelongApplication> applicationIdsInUser = userRoleRepository.findIdsApplicationByUser(Set.of(userId));

        boolean notEqualsApplicationRolesAndUserInApplication = false;

        if (notEqualsApplicationRolesAndUserInApplication) {
            //TODO
            throw new InfoException("");
        }


    }

}
