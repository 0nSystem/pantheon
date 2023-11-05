package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_PUBLIC;

@Entity
@Table(schema = SCHEME_PUBLIC, name = "language")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLanguage;

    @NotEmpty
    @Max(100)
    private String name;

    @Column(name = "iso_639_1_code")
    @NotEmpty
    @Max(50)
    private String iso6391Code;

    @NotEmpty
    @Max(100)
    private String languageFamily;

}