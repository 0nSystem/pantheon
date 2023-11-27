package com.onsystem.wscapp.pantheon.model.service.update;

import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdateApplicationService;
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
