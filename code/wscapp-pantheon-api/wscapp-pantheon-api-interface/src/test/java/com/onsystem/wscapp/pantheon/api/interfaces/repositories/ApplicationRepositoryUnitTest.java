package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationRepositoryUnitTest {

    @Autowired
    private ApplicationRepository applicationRepository;


    @Test
    @Rollback
    void findById()  {
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
