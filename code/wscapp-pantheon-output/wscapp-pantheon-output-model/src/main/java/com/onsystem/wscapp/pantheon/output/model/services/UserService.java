package com.onsystem.wscapp.pantheon.output.model.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationDataDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.ApplicationWithUsersData;
import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperApplicationWithUserData;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperUser;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeUserDataProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserPermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserRoleInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.output.api.interfaces.repositories.UserRepository;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IApplicationService;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMapperUser iMapperUser;

    @Autowired
    private IApplicationService iApplicationService;

    @Autowired
    private IMapperApplicationWithUserData iMapperApplicationWithUserData;

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndRole(int applicationId, List<Integer> roleIds) {
        return userRepository.findUserInApplicationByIdApplicationAndIdRoleInAndDeleteDateIsNull(applicationId, roleIds)
                .stream()
                .map(iMapperUser::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndRoleWithValidationIfCanShowThisInfo(int applicationId, List<Integer> roleIds) {
        validationShowApplicationInfo(List.of(applicationId));
        return findUsersByIdApplicationAndRole(applicationId, roleIds);
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndPermissions(int applicationId, List<Integer> permissionIds) {

        return userRepository.findUserInApplicationByIdApplicationAndIdPermissionInAndDeleteDateIsNull(applicationId, permissionIds)
                .stream()
                .map(iMapperUser::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndPermissionsWithValidationIfCanShowThisInfo(int applicationId, List<Integer> permissionIds) {
        validationShowApplicationInfo(List.of(applicationId));
        return findUsersByIdApplicationAndPermissions(applicationId, permissionIds);
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndIdAttribute(int applicationId, int attributeId) {
        return userRepository
                .findUserInApplicationByIdApplicationAndIdAttributeAndValue(applicationId, attributeId)
                .stream()
                .map(iMapperUser::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndIdAttributeWithValidationIfCanShowThisInfo(int applicationId, int attributeId) {
        validationShowApplicationInfo(List.of(applicationId));
        return findUsersByIdApplicationAndIdAttribute(applicationId, attributeId);
    }

    @Override
    public Set<ApplicationWithUsersData> findApplicationWithUserData(
            List<Integer> applicationIds,
            int languageId
    ) {

        validationShowApplicationInfo(applicationIds);

        final Collection<ApplicationDataDTO> applicationsData = iApplicationService.findByIdsApplications(applicationIds, languageId);

        final List<UserInfoProjection> userInApplications = userRepository
                .findUserInApplicationByIdApplication(applicationIds);
        final List<UserRoleInfoProjection> userRoleApplications = userRepository.findUserRoleByApplicationIdIn(
                applicationIds, languageId
        );
        final List<UserPermissionInfoProjection> userPermissionInfoInApplications = userRepository.findUserPermissionByApplicationIdIn(
                applicationIds,languageId
        );

        final List<AttributeUserDataProjection> attributeUsserDataProjections = userRepository
                .findAttributeDataUsersByIdApplication(applicationIds,languageId);


        return iMapperApplicationWithUserData.toApplicationWithUserData(
                applicationsData,
                userInApplications,
                userRoleApplications,
                userPermissionInfoInApplications,
                attributeUsserDataProjections
        );
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplication(int applicationId) {
        return userRepository.findUserInApplicationByIdApplication(List.of(applicationId))
                .stream()
                .map(iMapperUser::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationWithValidationIfCanShowThisInfo(int applicationId) {
        validationShowApplicationInfo(List.of(applicationId));
        return findUsersByIdApplication(applicationId);
    }

    private void validationShowApplicationInfo(List<Integer> applicationId) {

    }
}
