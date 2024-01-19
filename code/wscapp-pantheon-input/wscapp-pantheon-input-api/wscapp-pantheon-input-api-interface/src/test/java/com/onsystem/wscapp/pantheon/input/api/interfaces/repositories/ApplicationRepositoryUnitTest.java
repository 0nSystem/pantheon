package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationRepositoryUnitTest {

    @Autowired
    private ApplicationRepository applicationRepository;


    @Test
    @Rollback
    void findById() {
        final ApplicationEntity applicationEntity = applicationRepository.save(
                ApplicationEntity.builder()
                        .description("asd")
                        .name("asd")
                        .highDate(Timestamp.valueOf(LocalDateTime.now()))
                        .highIdUser(1)
                        .build()
        );

        assertThat(applicationEntity).isNotNull();

        final Optional<ApplicationEntity> getApplicationEntity = applicationRepository.findById(applicationEntity.getIdApplication());
        Assertions.assertTrue(getApplicationEntity.isPresent());
        assertThat(getApplicationEntity.get().getIdApplication()).isGreaterThan(0);
    }

    @Test
    @Rollback
    void findByApplicationName() {
        final String applicationName = "asd";
        final ApplicationEntity applicationEntity = applicationRepository.save(
                ApplicationEntity.builder()
                        .name(applicationName)
                        .description("asd")
                        .build()
        );

        assertThat(applicationEntity).isNotNull();
    }


}
