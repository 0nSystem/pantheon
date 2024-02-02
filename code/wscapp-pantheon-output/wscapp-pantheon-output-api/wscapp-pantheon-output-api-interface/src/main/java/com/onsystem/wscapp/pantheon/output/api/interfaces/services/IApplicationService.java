package com.onsystem.wscapp.pantheon.output.api.interfaces.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;

import java.util.List;
import java.util.Set;

public interface IApplicationService {


    AllInfoApplicationDTO findByIdApplication(
            final int applicationId,
            int languageId
    );

    Set<AllInfoApplicationDTO> findByIdsApplications(
            final List<Integer> applicationIds,
            int languageId
    );

    AllInfoApplicationDTO findByIdApplicationWithValidationIfCanShowThisInfo(
            final int applicationId,
            int languageId
    );

    Set<AllInfoApplicationDTO> findByIdsApplicationsWithValidationIfCanShowThisInfo(
            final List<Integer> applicationIds,
            int languageId
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

}
