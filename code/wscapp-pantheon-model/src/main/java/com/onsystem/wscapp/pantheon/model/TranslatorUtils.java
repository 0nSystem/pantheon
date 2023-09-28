package com.onsystem.wscapp.pantheon.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TranslatorUtils {
    @Autowired
    private MessageSource messageSource;


    public String createMessage(final String code) {
        return createMessage(code, null, null);
    }

    public String createMessage(final String code, final Locale locale, final Object... args) {
        return messageSource.getMessage(code, args, locale);
    }


}
