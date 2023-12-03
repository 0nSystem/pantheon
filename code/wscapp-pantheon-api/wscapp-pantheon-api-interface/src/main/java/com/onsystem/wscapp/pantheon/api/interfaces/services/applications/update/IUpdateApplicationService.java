package com.onsystem.wscapp.pantheon.api.interfaces.services.applications.update;

import com.onsystem.wscapp.pantheon.api.dto.applications.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.application.UpdateApplicationLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdateApplicationService {

    @Transactional
    void updateApplication(final Collection<UpdateApplicationDTO> updateApplication);

    @Transactional
    void updateApplicationLanguages(final Collection<UpdateApplicationLanguageDTO> updateApplicationLanguage);

}
