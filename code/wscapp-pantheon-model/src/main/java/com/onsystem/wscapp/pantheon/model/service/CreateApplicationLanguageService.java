package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.From;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.MapperApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ICreateApplicationLanguageService;
import com.onsystem.wscapp.pantheon.api.request.application.CreateApplicationLanguageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateApplicationLanguageService implements ICreateApplicationLanguageService {

    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;

    @Override
    public Set<ApplicationLanguageEntity> createApplicationLanguage(final Collection<CreateApplicationLanguageDTO> applicationLanguage, final int applicationId) {
        final var fnTransformCreateApplicationToApplicationEntity = MapperApplicationLanguageEntity.transformApplicationLanguageFromCreateApplicationLanguage(applicationId);
        final Set<ApplicationLanguageEntity> applicationLanguageMapped = applicationLanguage.stream().map(fnTransformCreateApplicationToApplicationEntity).collect(Collectors.toSet());
        return new HashSet<>(applicationLanguageRepository.saveAll(applicationLanguageMapped));

    }
}
