package com.onsystem.wscapp.pantheon.output.api.interfaces.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;

import java.util.Set;

public interface IApplicationService {


    AllInfoApplicationDTO findByIdApplication(final Integer applicationId);

    Set<AllInfoApplicationDTO> findByIdsApplications(final Integer applicationsIds);



}
