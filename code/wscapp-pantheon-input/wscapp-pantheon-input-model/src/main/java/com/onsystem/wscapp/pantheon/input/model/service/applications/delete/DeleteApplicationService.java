package com.onsystem.wscapp.pantheon.input.model.service.applications.delete;

import com.onsystem.wscapp.pantheon.input.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.delete.IDeleteApplicationService;
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
        applicationRepository.updateDeleteDateAndDeleteIdUserByIdApplicationIn(iTimeHelper.now(), userDeleteId, applicationIds);
    }

    @Override
    public void deleteApplicationLanguage(int applicationId, Collection<Integer> languageIds) {

        applicationLanguageRepository.deleteByIdApplicationAndIdLanguageIn(applicationId, languageIds);
    }
}
