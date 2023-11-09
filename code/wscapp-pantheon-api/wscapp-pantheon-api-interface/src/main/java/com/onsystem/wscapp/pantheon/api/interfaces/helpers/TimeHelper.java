package com.onsystem.wscapp.pantheon.api.interfaces.helpers;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//TODO
@Service
public interface TimeHelper {

    default @NonNull Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
