package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttributeLanguageKeyEntity {

    @NotNull
    private Integer attribute;
    @NotNull
    private Integer language;
}
