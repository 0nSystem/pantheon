package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = "attribute_language")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AttributeLanguageKeyEntity.class)
public class AttributeLanguageEntity {

    @Id
    @NotNull
    private Integer idAttribute;
    @Id
    @NotNull
    private Integer idLanguage;

    @NotEmpty
    @Max(100)
    private String name;
    @NotEmpty
    @Max(255)
    private String description;

}
