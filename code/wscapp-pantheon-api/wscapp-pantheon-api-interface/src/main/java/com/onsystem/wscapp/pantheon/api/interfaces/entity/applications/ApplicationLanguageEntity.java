package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.spublic.LanguageEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_APPLICATION_LANGUAGE;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_APPLICATION_LANGUAGE)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ApplicationLanguageKeyEntity.class)
public class ApplicationLanguageEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idApplication")
    private ApplicationEntity application;

    @Id
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "idLanguage")
    private LanguageEntity language;
}
