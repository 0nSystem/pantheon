package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.request.application.CreateFullApplicationDTO;
import jakarta.validation.constraints.NotNull;

public interface ICreateApplicationService {

    void createFullApplication(final @NotNull CreateFullApplicationDTO application);
}
