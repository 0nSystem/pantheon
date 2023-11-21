package com.onsystem.wscapp.pantheon.api.interfaces.helpers;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//TODO
@Component
public interface ITimeHelper {

    default @NonNull Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
