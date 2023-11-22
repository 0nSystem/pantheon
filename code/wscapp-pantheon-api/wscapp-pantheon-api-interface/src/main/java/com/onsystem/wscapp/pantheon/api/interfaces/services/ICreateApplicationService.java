package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.CreateApplicationLanguageDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Collection;
import java.util.Set;

public interface ICreateApplicationService {

    @NotNull ApplicationDTO createApplication(final @NotNull CreateApplicationDTO createApplication);

    @NotNull Set<ApplicationLanguageDTO> createApplicationLanguages(
            final @Positive int applicationId,
            final @NotNull @NotEmpty Collection<CreateApplicationLanguageDTO> createApplicationLanguage);
}
