package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "application_language")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ApplicationLanguageKeyEntity.class)
public class ApplicationLanguageEntity {

    @Id
    @NotNull
    private Integer idApplication;
    @Id
    @NotNull
    private Integer idLanguage;

    private String name;
    private String description;
}
