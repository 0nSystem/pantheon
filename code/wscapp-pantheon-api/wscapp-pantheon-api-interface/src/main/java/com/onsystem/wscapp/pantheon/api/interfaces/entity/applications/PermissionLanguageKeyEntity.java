package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;

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
    private Integer permission;

    @NotNull
    private Integer language;
}
