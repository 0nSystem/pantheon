package com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications;

import com.onsystem.wscapp.pantheon.input.api.interfaces.Constants;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.spublic.LanguageEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = Constants.TABLE_ATTRIBUTE_LANGUAGE)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AttributeLanguageKeyEntity.class)
public class AttributeLanguageEntity {



    @NotEmpty
    @Max(100)
    private String name;
    @NotEmpty
    @Max(255)
    private String description;


    @Id
    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttribute")
    private AttributeEntity attribute;

    @Id
    @NotNull
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLanguage")
    private LanguageEntity language;

}
