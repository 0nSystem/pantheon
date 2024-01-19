package com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLanguageKeyEntity {
    @NotNull
    private Integer application;
    @NotNull
    private Integer language;
}
