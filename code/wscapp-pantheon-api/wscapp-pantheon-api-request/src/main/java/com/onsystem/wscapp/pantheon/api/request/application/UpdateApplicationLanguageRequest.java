package com.onsystem.wscapp.pantheon.api.request.application;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UpdateApplicationLanguageRequest {

    private ApplicationLanguageKeyRequest applicationLanguageKeyEntity;

    private @NonNull String name;
    private String description;
}
