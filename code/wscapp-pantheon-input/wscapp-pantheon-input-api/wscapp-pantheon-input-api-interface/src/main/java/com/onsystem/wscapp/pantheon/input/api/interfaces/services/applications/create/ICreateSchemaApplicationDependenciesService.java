package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationFullInfoWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateFullApplicationDTO;
import jakarta.validation.constraints.NotNull;

/**
 * This service used to create entities reference by schema applications
 */
public interface ICreateSchemaApplicationDependenciesService {

    @NotNull ApplicationFullInfoWithLanguagesDTO createFullApplication(final @NotNull CreateFullApplicationDTO application);

}
