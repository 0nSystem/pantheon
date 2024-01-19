package com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeLanguageKeyEntity {

    @NotNull
    private Integer attribute;
    @NotNull
    private Integer language;
}
