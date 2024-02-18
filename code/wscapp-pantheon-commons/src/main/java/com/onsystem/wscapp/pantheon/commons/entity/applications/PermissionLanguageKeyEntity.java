package com.onsystem.wscapp.pantheon.commons.entity.applications;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionLanguageKeyEntity {

    @NotNull
    private Integer permission;

    @NotNull
    private Integer language;
}
