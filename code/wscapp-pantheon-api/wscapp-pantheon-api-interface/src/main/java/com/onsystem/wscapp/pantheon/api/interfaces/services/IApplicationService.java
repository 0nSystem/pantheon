package com.onsystem.wscapp.pantheon.api.interfaces.services;


import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IApplicationService {

    List<ApplicationEntity> create(final ApplicationEntity... applicationEntities);
    void update(final ApplicationEntity... applicationEntities);
    ApplicationEntity findById(final @Valid @Positive int applicationId);
    void delete(final @Valid @NotEmpty @Positive Integer... applicationId);

}
