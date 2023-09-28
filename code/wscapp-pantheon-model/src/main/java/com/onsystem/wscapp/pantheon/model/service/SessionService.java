package com.onsystem.wscapp.pantheon.model.service;


import com.onsystem.wscapp.pantheon.api.interfaces.services.ISessionService;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
//TODO
public class SessionService implements ISessionService {


    public Locale getLocaleInUser() {

        return LocaleUtils.toLocale("es");
    }

    @Override
    public String getUserNameInSession() {
        return "MockUsername";
    }

    @Override
    public Integer getUserIdInSession() {
        return 1;
    }


}
