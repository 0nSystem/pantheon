package com.onsystem.wscapp.pantheon.api.interfaces.repositories;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ApplicationLanguageEntityUnitTest {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;

}
