package com.onsystem.wscapp.pantheon.api.interfaces.services;

import org.springframework.validation.annotation.Validated;

import java.util.Locale;

@Validated
public interface ISessionService {
    Locale getLocaleInUser();
    String getUserNameInSession();
    Integer getUserIdInSession();
}
