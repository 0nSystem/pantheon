package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories;


import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.commons.entity.spublic.LanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.spublic.LanguageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationLanguageEntityUnitTest {

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;


    @Test
    @Transactional
    public void createApplicationLanguage() {
        final var languageEntity = languageRepository.save(LanguageEntity.builder()
                .languageFamily("asd")
                .name("asd").iso6391Code("ES")
                .build());

        final ApplicationEntity applicationEntity = applicationRepository.save(
                ApplicationEntity.builder()
                        .description("asd")
                        .name("asd")
                        .highDate(Timestamp.valueOf(LocalDateTime.now()))
                        .highIdUser(1)
                        .build()
        );

        final ApplicationLanguageEntity applicationLanguageEntity = applicationLanguageRepository.save(
                ApplicationLanguageEntity.builder()
                        .language(LanguageEntity.builder().idLanguage(languageEntity.getIdLanguage()).build())
                        .application(ApplicationEntity.builder().idApplication(applicationEntity.getIdApplication()).build())
                        .name("language name")
                        .description("language description")
                        .build()
        );


        Assertions.assertNotNull(applicationLanguageEntity);
        Assertions.assertEquals(applicationEntity, applicationLanguageEntity.getApplication());
        Assertions.assertEquals(languageEntity, applicationLanguageEntity.getLanguage());

    }

}
