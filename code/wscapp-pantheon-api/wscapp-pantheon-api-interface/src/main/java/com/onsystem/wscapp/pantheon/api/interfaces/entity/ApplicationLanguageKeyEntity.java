package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLanguageKeyEntity {
    @NotNull
    private Integer application;
    @NotNull
    private Integer language;
}
