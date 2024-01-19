package com.onsystem.wscapp.pantheon.input.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ConfigModelModule {



    //TODO cambiar a api-interface?
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // Establecer el basename para la ubicación de los archivos de mensajes en el classpath
        messageSource.setBasenames("classpath:messages/validation","classpath:messages/InfoGeneral");
        //messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

