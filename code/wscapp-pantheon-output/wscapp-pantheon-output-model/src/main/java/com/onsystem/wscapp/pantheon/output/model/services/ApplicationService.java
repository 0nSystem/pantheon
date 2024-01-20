package com.onsystem.wscapp.pantheon.output.model.services;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public AllInfoApplicationDTO findByIdApplication(Integer applicationId) {



        return null;
    }

    @Override
    public Set<AllInfoApplicationDTO> findByIdsApplications(Integer applicationsIds) {
        return null;
    }
}
