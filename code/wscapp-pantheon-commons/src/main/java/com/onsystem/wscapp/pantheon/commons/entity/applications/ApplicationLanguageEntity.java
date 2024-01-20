package com.onsystem.wscapp.pantheon.commons.entity.applications;

import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.entity.spublic.LanguageEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;



@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = Constants.TABLE_APPLICATION_LANGUAGE)
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
