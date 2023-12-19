package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserRoleService;
import com.onsystem.wscapp.pantheon.model.Constants;
import com.onsystem.wscapp.pantheon.model.Utils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserRoleService implements ICreateUserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void assignRole(List<Integer> roleIds, int userId) {
        validateNotRepeatedArguments(roleIds);
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

    private void validateUserNotContainsRolesInDataBase(List<Integer> roleIds, int userId) {
        final int countUserWithRoles = userRoleRepository.countUserRoleEntitiesByIdRoleInAndIdUser(roleIds, userId);

        if (countUserWithRoles > 0) {
            //FIXME
            throw new InfoException("");
        }

    }

    private void validateNotRepeatedArguments(final List<Integer> roleIds) {
        boolean argumetsRolesIdsIsRepeated = Utils.elementsRepeatedInList(roleIds, null);

        if (argumetsRolesIdsIsRepeated) {
            //FIXME
            throw new InfoException("");
        }
    }

    private void validateUserBelongsApplicationToAssingRoles(final List<Integer> roleIds, final int userId) {
        final List<Integer> applicationIdsInRoles = roleRepository.findIdsApplicationByIdRoleIn(roleIds);


        final List<Integer> applicationIdsInUser = userRoleRepository.findIdsApplicationByUser(userId, Constants.AUTORIZED_PERMISSION_NAME);

        boolean notEqualsApplicationRolesAndUserInApplication = false;

        if (notEqualsApplicationRolesAndUserInApplication) {
            //TODO
            throw new InfoException("");
        }


    }

}
