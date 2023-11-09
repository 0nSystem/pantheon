package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.dto.application.CreateFullApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.application.ApplicationFullInfoWithLanguagesDTO;
import jakarta.validation.constraints.NotNull;

public interface ICreateFullApplicationService {

    @NotNull ApplicationFullInfoWithLanguagesDTO createFullApplication(final @NotNull CreateFullApplicationDTO application);
}
