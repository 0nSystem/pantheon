package com.onsystem.wscapp.pantheon.output.model.services;

import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperUser;
import com.onsystem.wscapp.pantheon.output.api.interfaces.repositories.UserRepository;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMapperUser iMapperUser;
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
    public Set<UserInfoDTO> findUsersByIdApplication(int applicationId) {
        return userRepository.findUserInApplicationByIdApplication(applicationId)
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
