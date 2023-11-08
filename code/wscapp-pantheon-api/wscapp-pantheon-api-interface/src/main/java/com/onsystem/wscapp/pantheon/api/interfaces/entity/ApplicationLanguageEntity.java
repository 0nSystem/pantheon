package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "application_language")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLanguageEntity {

    @EmbeddedId
    private ApplicationLanguageKeyEntity applicationLanguageKeyEntity;

    private String name;
    private String description;
}
