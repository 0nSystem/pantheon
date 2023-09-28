package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "application_language")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLanguageEntity {

    @EmbeddedId
    private ApplicationLanguageKeyEntity applicationLanguageKeyEntity;

    private String name;
    private String description;
}
