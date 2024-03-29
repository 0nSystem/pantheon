package com.onsystem.wscapp.pantheon.output.model.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationDataDTO;
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
    public ApplicationDataDTO findByIdApplication(int applicationId, int languageId) {

        return findByIdsApplications(List.of(applicationId), languageId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Error applicationId: %s , not found", applicationId)));
    }

    @Override
    public Set<ApplicationDataDTO> findByIdsApplications(List<Integer> applicationIds, int languageId) {
        final var mapIdApplicationApplicationProjectionInfo = applicationRepository
                .findApplicationInfoById(languageId, applicationIds)
                .stream()
                .collect(Collectors.toMap(
                        ApplicationInfoProjection::getIdApplication,
                        o -> iMapperApplication.toDto(o),
                        (t, t2) -> t));

        final var permissions = applicationRepository
                .findPermissionInfoProjectionByIdApplicationIn(languageId, applicationIds);
        final var mapIdApplicationPermissionProjectionApplications = permissions
                .stream()
                .collect(Collectors.groupingBy(
                        PermissionInfoProjection::getIdApplication,
                        Collectors.mapping(iMapperPermission::toDto, Collectors.toList())
                ));

        final var roles = applicationRepository
                .findRoleInfoProjectionByIdApplicationIn(languageId, applicationIds);
        final var mapIdApplicationRolesProjectionApplications = roles
                .stream()
                .collect(Collectors.groupingBy(
                        RoleInfoProjection::getIdApplication,
                        Collectors.mapping(iMapperRole::toDto, Collectors.toList())));

        final var attributes = applicationRepository
                .findAttributeInfoProjectionByIdApplicationIn(languageId, applicationIds);
        final var mapIdApplicationAttributesApplications = attributes
                .stream()
                .collect(Collectors.groupingBy(AttributeInfoProjection::getIdApplication,
                        Collectors.mapping(iMapperAttribute::toDto, Collectors.toList())));


        return mapIdApplicationApplicationProjectionInfo.entrySet()
                .stream()
                .map(entryIdApplicationApplicationProjectInfo -> ApplicationDataDTO.builder()
                        .applicationInfo(entryIdApplicationApplicationProjectInfo.getValue())
                        .rolesInfo(mapIdApplicationRolesProjectionApplications.get(entryIdApplicationApplicationProjectInfo.getKey()))
                        .permissionsInfo(mapIdApplicationPermissionProjectionApplications.get(entryIdApplicationApplicationProjectInfo.getKey()))
                        .attributesInfo(mapIdApplicationAttributesApplications.get(entryIdApplicationApplicationProjectInfo.getKey()))
                        .build())
                .collect(Collectors.toSet());
    }

    @Override
    public ApplicationDataDTO findByIdApplicationWithValidationIfCanShowThisInfo(int applicationId, int languageId) {

        return findByIdsApplicationsWithValidationIfCanShowThisInfo(List.of(applicationId), languageId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Error not found application %s", applicationId)));
    }

    @Override
    public Set<ApplicationDataDTO> findByIdsApplicationsWithValidationIfCanShowThisInfo(List<Integer> applicationIds, int languageId) {
        validationShowApplicationInfo(applicationIds);
        return findByIdsApplications(applicationIds, languageId);
    }


    private void validationShowApplicationInfo(List<Integer> applicationIds) {
        //TODO
    }


}
