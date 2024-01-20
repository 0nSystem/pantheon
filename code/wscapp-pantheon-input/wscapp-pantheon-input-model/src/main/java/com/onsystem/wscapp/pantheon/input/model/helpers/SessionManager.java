package com.onsystem.wscapp.pantheon.input.model.helpers;

import com.onsystem.wscapp.pantheon.input.api.interfaces.services.ISessionManager;
import org.springframework.stereotype.Service;

@Service
public class SessionManager implements ISessionManager {
    @Override
    public Integer currentIdUser() {
        //TODO
        return 1;
    }
}
