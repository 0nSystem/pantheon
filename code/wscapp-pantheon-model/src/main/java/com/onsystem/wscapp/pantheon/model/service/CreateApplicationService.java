package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateApplicationLanguageService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.api.request.application.CreateFullApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CreateApplicationService implements ICreateApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ICreateApplicationLanguageService iCreateApplicationLanguageService;


    @Override
    //TODO change return
    public void createFullApplication(final CreateFullApplicationDTO application) {
        final ApplicationEntity applicationEntityMapped = MapperApplicationEntity.mapperApplicationEntityFromCreateApplication()
                .from(application.getApplication());

        final ApplicationEntity applicationInserted = applicationRepository.save(applicationEntityMapped);

        if (application.getApplicationLanguages() != null && !application.getApplicationLanguages().isEmpty()) {
            final Collection<ApplicationLanguageEntity> languageEntities = iCreateApplicationLanguageService.createApplicationLanguage(application.getApplicationLanguages(), applicationInserted.getIdApplication());
        }





    }
}
