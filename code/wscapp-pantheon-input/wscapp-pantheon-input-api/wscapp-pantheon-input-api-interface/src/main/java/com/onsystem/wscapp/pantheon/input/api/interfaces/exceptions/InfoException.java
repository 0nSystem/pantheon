package com.onsystem.wscapp.pantheon.input.api.interfaces.exceptions;


import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class InfoException extends RuntimeException {

    private final String[] extraInfoLogs;

    public InfoException(final String messageToReturn, final String... extraInfoLogs) {
        super(messageToReturn);
        this.extraInfoLogs = extraInfoLogs;
    }
}
