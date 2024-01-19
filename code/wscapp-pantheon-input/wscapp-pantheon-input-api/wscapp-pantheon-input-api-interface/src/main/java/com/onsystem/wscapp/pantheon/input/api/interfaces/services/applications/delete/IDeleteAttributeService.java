package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IDeleteAttributeService {
    @Transactional
    void deleteAttribute(final @NotEmpty Collection<Integer> attributeIds);

    @Transactional
    void deleteAttributeLanguage(final @Positive int attributeId,
                                 final @NotEmpty Collection<Integer> languageIds);
}
