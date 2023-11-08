package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PermissionLanguageKeyEntity {

    @NotNull
    private Integer idPermission;

    @NotNull
    private Integer idLanguage;
}
