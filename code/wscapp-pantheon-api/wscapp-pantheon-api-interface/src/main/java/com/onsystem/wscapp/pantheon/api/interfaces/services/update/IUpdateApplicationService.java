package com.onsystem.wscapp.pantheon.api.interfaces.services.update;

import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdateApplicationService {

    @Transactional
    void updateApplication(final Collection<UpdateApplicationDTO> updateApplication);

    @Transactional
    void updateApplicationLanguages(final Collection<UpdateApplicationLanguageDTO> updateApplicationLanguage);

}
