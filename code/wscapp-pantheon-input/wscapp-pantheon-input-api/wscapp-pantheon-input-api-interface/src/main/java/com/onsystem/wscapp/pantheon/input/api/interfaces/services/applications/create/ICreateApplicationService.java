package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationLanguageDTO;
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
