package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.interfaces.helpers.ITimeHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ITimeHelper timeHelper() {
        return new ITimeHelper() {
        };
    }

}


