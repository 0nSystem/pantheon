package com.onsystem.wscapp.pantheon.input.model.service.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update.IUpdateApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UpdateApplicationService implements IUpdateApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;


    @Override
    public void updateApplication(Collection<UpdateApplicationDTO> updateApplication) {
        updateApplication.forEach(applicationRepository::update);
    }

    @Override
    public void updateApplicationLanguages(Collection<UpdateApplicationLanguageDTO> updateApplicationLanguage) {

        updateApplicationLanguage.forEach(applicationLanguageRepository::update);
    }
}
