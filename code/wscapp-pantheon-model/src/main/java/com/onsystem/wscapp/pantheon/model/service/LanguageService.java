package com.onsystem.wscapp.pantheon.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.LanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ILanguageService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ISessionService;
import com.onsystem.wscapp.pantheon.model.Constants;
import com.onsystem.wscapp.pantheon.model.TranslatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class LanguageService implements ILanguageService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private ISessionService sessionService;

    @Autowired
    private TranslatorUtils translatorUtils;

    @Override
    public List<LanguageEntity> create(LanguageEntity... createLanguage) {
        return languageRepository.saveAll(Arrays.asList(createLanguage));
    }

    @Override
    public List<LanguageEntity> update(LanguageEntity... updateLanguages) {
        return languageRepository.saveAll(Arrays.asList(updateLanguages));
    }

    @Override
    public List<LanguageEntity> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public LanguageEntity findById(int idLanguage) {
        return languageRepository.findById(idLanguage)
                .orElseThrow(() -> {
                            final Locale locale = sessionService.getLocaleInUser();
                            final String messageEntity = translatorUtils.createMessage(Constants.InfoMessages.ENTITY_LANGUAGE, locale);
                            return new InfoException(translatorUtils.createMessage(Constants.InfoMessages.NOT_FOUND_WITH_MESSAGE, locale, messageEntity));
                        }
                );
    }
}
