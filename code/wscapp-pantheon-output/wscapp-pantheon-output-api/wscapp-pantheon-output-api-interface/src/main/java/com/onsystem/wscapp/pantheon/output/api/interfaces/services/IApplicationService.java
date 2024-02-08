package com.onsystem.wscapp.pantheon.output.api.interfaces.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationDataDTO;

import java.util.List;
import java.util.Set;

public interface IApplicationService {


    ApplicationDataDTO findByIdApplication(
            final int applicationId,
            int languageId
    );

    Set<ApplicationDataDTO> findByIdsApplications(
            final List<Integer> applicationIds,
            int languageId
    );

    ApplicationDataDTO findByIdApplicationWithValidationIfCanShowThisInfo(
            final int applicationId,
            int languageId
    );

    Set<ApplicationDataDTO> findByIdsApplicationsWithValidationIfCanShowThisInfo(
            final List<Integer> applicationIds,
            int languageId
    );



}
