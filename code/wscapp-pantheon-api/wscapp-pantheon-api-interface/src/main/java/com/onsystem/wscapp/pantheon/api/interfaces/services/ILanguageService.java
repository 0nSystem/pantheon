package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;
import com.onsystem.wscapp.pantheon.api.request.language.CreateLanguageRequest;
import com.onsystem.wscapp.pantheon.api.request.language.UpdateLanguageRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ILanguageService {

    List<LanguageEntity> create(final LanguageEntity... createLanguage);
    List<LanguageEntity> update(final LanguageEntity... updateLanguages);
    List<LanguageEntity> findAll();
    LanguageEntity findById(final @Valid @Positive int idLanguage);
}
