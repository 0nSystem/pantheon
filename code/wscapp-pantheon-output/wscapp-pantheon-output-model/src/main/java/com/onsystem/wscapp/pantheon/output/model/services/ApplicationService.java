package com.onsystem.wscapp.pantheon.output.model.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.*;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.ApplicationInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.PermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.RoleInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private IMapperApplication iMapperApplication;
    @Autowired
    private IMapperPermission iMapperPermission;
    @Autowired
    private IMapperAttribute iMapperAttribute;
    @Autowired
    private IMapperRole iMapperRole;
    @Autowired
    private IMapperUser iMapperUser;


    @Override
    public AllInfoApplicationDTO findByIdApplication(int applicationId, int languageId) {

        return findByIdsApplications(List.of(applicationId), languageId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Error applicationId: %s , not found", applicationId)));
    }

    @Override
    public Set<AllInfoApplicationDTO> findByIdsApplications(List<Integer> applicationIds, int languageId) {
        final var mapIdApplicationApplicationProjectionInfo = applicationRepository
                .findApplicationInfoById(languageId, applicationIds)
                .stream()
                .collect(Collectors.toMap(
                        ApplicationInfoProjection::getIdApplication,
                        o -> iMapperApplication.toDto(o),
                        (t, t2) -> t));

        final var mapIdApplicationPermissionProjectionApplications = applicationRepository
                .findPermissionInfoProjectionByIdApplicationIn(languageId, applicationIds)
                .stream()
                .collect(Collectors.groupingBy(
                        PermissionInfoProjection::getIdApplication,
                        Collectors.mapping(iMapperPermission::toDto, Collectors.toList())
                ));

        final var mapIdApplicationRolesProjectionApplications = applicationRepository
                .findRoleInfoProjectionByIdApplicationIn(languageId, applicationIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoleInfoProjection::getIdApplication,
                        Collectors.mapping(iMapperRole::toDto, Collectors.toList())));

        final var mapIdApplicationAttributesApplications = applicationRepository
                .findAttributeInfoProjectionByIdApplicationIn(languageId, applicationIds)
                .stream()
                .collect(Collectors.groupingBy(AttributeInfoProjection::getIdApplication,
                        Collectors.mapping(iMapperAttribute::toDto, Collectors.toList())));


        return mapIdApplicationApplicationProjectionInfo.entrySet()
                .stream()
                .map(entryIdApplicationApplicationProjectInfo -> AllInfoApplicationDTO.builder()
                        .applicationInfo(entryIdApplicationApplicationProjectInfo.getValue())
                        .rolesInfo(mapIdApplicationRolesProjectionApplications.get(entryIdApplicationApplicationProjectInfo.getKey()))
                        .permissionsInfo(mapIdApplicationPermissionProjectionApplications.get(entryIdApplicationApplicationProjectInfo.getKey()))
                        .attributesInfo(mapIdApplicationAttributesApplications.get(entryIdApplicationApplicationProjectInfo.getKey()))
                        .build())
                .collect(Collectors.toSet());
    }

    @Override
    public AllInfoApplicationDTO findByIdApplicationWithValidationIfCanShowThisInfo(int applicationId, int languageId) {

        return findByIdsApplicationsWithValidationIfCanShowThisInfo(List.of(applicationId), languageId)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Error not found application %s", applicationId)));
    }

    @Override
    public Set<AllInfoApplicationDTO> findByIdsApplicationsWithValidationIfCanShowThisInfo(List<Integer> applicationIds, int languageId) {
        validationShowApplicationInfo(applicationIds);
        return findByIdsApplications(applicationIds, languageId);
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndRole(int applicationId, List<Integer> roleIds) {
        return applicationRepository.findUserByIdApplicationAndIdRoleInAndDeleteDateIsNull(applicationId, roleIds)
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

        return applicationRepository.findUserByIdApplicationAndIdPermissionInAndDeleteDateIsNull(applicationId,permissionIds)
                .stream()
                .map(iMapperUser::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserInfoDTO> findUsersByIdApplicationAndPermissionsWithValidationIfCanShowThisInfo(int applicationId, List<Integer> permissionIds) {
        validationShowApplicationInfo(List.of(applicationId));
        return findUsersByIdApplicationAndPermissions(applicationId, permissionIds);
    }

    private void validationShowApplicationInfo(List<Integer> applicationIds) {
        //TODO
    }


}
