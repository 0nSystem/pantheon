package com.onsystem.wscapp.pantheon.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorValidationObject<T> {

    private T object;
    private Map<String, List<String>> messageErrors;
}