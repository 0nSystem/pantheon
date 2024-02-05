package com.onsystem.wscapp.pantheon.output.api.interfaces.services;

import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;

import java.util.List;
import java.util.Set;

public interface IUserService {
    Set<UserInfoDTO> findUsersByIdApplication(
            final int applicationId
    );

    Set<UserInfoDTO> findUsersByIdApplicationWithValidationIfCanShowThisInfo(
            final int applicationId
    );

    Set<UserInfoDTO> findUsersByIdApplicationAndRole(
            final int applicationId,
            final List<Integer> roleIds
    );

    Set<UserInfoDTO> findUsersByIdApplicationAndRoleWithValidationIfCanShowThisInfo(
            final int applicationId,
            final List<Integer> roleIds
    );


    Set<UserInfoDTO> findUsersByIdApplicationAndPermissions(
            final int applicationId,
            final List<Integer> permissionIds
    );
    Set<UserInfoDTO> findUsersByIdApplicationAndPermissionsWithValidationIfCanShowThisInfo(
            final int applicationId,
            final List<Integer> permissionIds
    );

    Set<UserInfoDTO> findUsersByIdApplicationAndIdAttribute(
            final int applicationId,
            final int attributeId
    );
    Set<UserInfoDTO> findUsersByIdApplicationAndIdAttributeWithValidationIfCanShowThisInfo(
            final int applicationId,
            final int attributeId
    );
}
