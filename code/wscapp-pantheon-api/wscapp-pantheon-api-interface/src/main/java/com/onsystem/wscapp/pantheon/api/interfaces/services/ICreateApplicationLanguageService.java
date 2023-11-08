package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.request.application.CreateApplicationLanguageDTO;

import java.util.Collection;
import java.util.Set;

public interface ICreateApplicationLanguageService {

    Set<ApplicationLanguageEntity> createApplicationLanguage(final Collection<CreateApplicationLanguageDTO> applicationLanguage, final int applicationId);
}
