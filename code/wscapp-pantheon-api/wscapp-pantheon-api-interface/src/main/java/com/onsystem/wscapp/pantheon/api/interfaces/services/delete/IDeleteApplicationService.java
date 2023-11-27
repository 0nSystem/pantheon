package com.onsystem.wscapp.pantheon.api.interfaces.services.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IDeleteApplicationService {
    @Transactional
    void deleteApplication(final @Positive int userId,
                           final @NotEmpty Collection<Integer> applicationIds);

    @Transactional
    void deleteApplicationLanguage(final @Positive int applicationIds,
                                   final @NotEmpty Collection<Integer> languageIds);
}
