package com.onsystem.wscapp.pantheon.output.api.interfaces.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;

import java.util.List;
import java.util.Set;

public interface IApplicationService {


    AllInfoApplicationDTO findByIdApplication(final int applicationId, int languageId);

    Set<AllInfoApplicationDTO> findByIdsApplications(final List<Integer> applicationIds, int languageId);


}
