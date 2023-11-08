package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = "attribute_language")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttributeLanguageEntity {

    @EmbeddedId
    private AttributeLanguageKeyEntity attributeLanguageKeyEntity;

    @NotEmpty
    @Max(100)
    private String name;
    @NotEmpty
    @Max(255)
    private String description;

}
