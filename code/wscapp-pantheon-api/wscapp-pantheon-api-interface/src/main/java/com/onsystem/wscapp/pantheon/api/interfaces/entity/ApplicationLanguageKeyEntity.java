package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLanguageKeyEntity {
    private Integer idApplication;
    private Integer idLanguage;
}
