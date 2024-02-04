package com.onsystem.wscapp.pantheon.input.model.service.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationFullInfoWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateFullApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateFullApplicationService implements ICreateSchemaApplicationDependenciesService {
    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private ICreatePermissionService iCreatePermissionService;
    @Autowired
    private ICreateRoleService iCreateRoleService;
    @Autowired
    private ICreateAttributeService iCreateAttributeService;




    @Override
    public ApplicationFullInfoWithLanguagesDTO createFullApplication(final CreateFullApplicationDTO createApplication) {
        final var application = iCreateApplicationService.createApplication(createApplication.getApplication());

        final var applicationLanguages = CollectionUtils.isNotEmpty(createApplication.getApplicationLanguages())
                ? iCreateApplicationService.createApplicationLanguages(application.getIdApplication(), createApplication.getApplicationLanguages())
                : null;

        final var applicationPermission = CollectionUtils.isNotEmpty(createApplication.getApplicationPermissions())
                ? iCreatePermissionService.createPermissionWithLanguages(application.getIdApplication(), null, createApplication.getApplicationPermissions())
                : null;

        final var applicationRoles = CollectionUtils.isNotEmpty(createApplication.getApplicationRoles())
                ? iCreateRoleService.createRoleAndRespectiveLanguagesWithInnerPermissionAndLanguage(application.getIdApplication(), createApplication.getApplicationRoles())
                : null;

        final var applicationAttributes = CollectionUtils.isNotEmpty(createApplication.getApplicationAttributes())
                ? iCreateAttributeService.createAttributesWithLanguages(application.getIdApplication(), createApplication.getApplicationAttributes())
                : null;

        return ApplicationFullInfoWithLanguagesDTO
                .builder()
                .application(application)
                .applicationLanguages(applicationLanguages)
                .applicationPermissions(applicationPermission)
                .applicationRoles(applicationRoles)
                .applicationAttributes(applicationAttributes)
                .build();

    }

}
