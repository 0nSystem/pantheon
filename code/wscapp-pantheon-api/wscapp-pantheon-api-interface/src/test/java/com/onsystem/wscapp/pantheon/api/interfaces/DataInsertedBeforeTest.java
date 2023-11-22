package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DataInsertedBeforeTest {

    @Bean
    public Integer idLanguage(@Autowired LanguageRepository languageRepository) {
        return languageRepository.save(MockData.DataMockSchemeApplicationEntities.LANGUAGE_MOCK).getIdLanguage();
    }

    @Bean
    public Integer idApplication(@Autowired ApplicationRepository applicationRepository){
        return applicationRepository.save(MockData.DataMockSchemeApplicationEntities.APPLICATION_MOCK).getIdApplication();
    }




}
