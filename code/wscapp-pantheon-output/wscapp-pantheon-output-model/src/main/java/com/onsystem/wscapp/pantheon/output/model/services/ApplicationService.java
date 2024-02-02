package com.onsystem.wscapp.pantheon.output.model.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperApplication;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperAttribute;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperPermission;
import com.onsystem.wscapp.pantheon.output.api.interfaces.mappers.IMapperRole;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.ApplicationInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.PermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.RoleInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


    @Override
    public AllInfoApplicationDTO findByIdApplication(int applicationId, int languageId) {

        return findByIdsApplications(List.of(applicationId), languageId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Error applicationId: %s , not found", applicationId)));
    }

    @Override
    public Set<AllInfoApplicationDTO> findByIdsApplications(List<Integer> applicationIds, int languageId) {
        validationShowApplicationInfo(applicationIds);
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

    private void validationShowApplicationInfo(List<Integer> applicationIds) {
        //TODO
    }
}
