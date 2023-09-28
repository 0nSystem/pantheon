package com.onsystem.wscapp.pantheon.api.request.application;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Builder
@Data
public class UpdateApplicationRequest {

    private Integer idApplication;
    private @NonNull String name;
    private String description;

    private List<UpdateApplicationLanguageRequest> updateApplicationLanguageEntities;
}
