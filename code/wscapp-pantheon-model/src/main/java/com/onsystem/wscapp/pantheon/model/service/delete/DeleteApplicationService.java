package com.onsystem.wscapp.pantheon.model.service.delete;

import com.onsystem.wscapp.pantheon.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.delete.IDeleteApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DeleteApplicationService implements IDeleteApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;
    @Autowired
    private ITimeHelper iTimeHelper;


    @Override
    public void deleteApplication(final int userDeleteId, Collection<Integer> applicationIds) {
        applicationRepository.updateDelete(iTimeHelper.now(), userDeleteId, applicationIds);
    }

    @Override
    public void deleteApplicationLanguage(int applicationIds, Collection<Integer> languageIds) {
        applicationLanguageRepository.delete(applicationIds, languageIds);
    }
}
