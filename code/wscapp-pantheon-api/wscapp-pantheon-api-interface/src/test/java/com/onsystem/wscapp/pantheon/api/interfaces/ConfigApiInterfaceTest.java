package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.interfaces.helpers.ITimeHelper;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ISessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApiInterfaceTest {

    @Bean
    @ConditionalOnMissingBean
    public ITimeHelper timeHelper() {
        return new ITimeHelper() {
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public ISessionManager sessionManager() {
        return new ISessionManager() {
            @Override
            public Integer currentIdUser() {
                return 1;
            }
        };
    }

}


