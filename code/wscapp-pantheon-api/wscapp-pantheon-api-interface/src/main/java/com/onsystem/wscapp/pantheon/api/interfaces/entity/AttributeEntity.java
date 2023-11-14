package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_ATTRIBUTE;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_ATTRIBUTE)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAttribute;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotEmpty
    @Size(max = 255)
    private String description;


    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idApplication")
    private ApplicationEntity application;


    @ToString.Exclude
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
    private Set<AttributeLanguageEntity> attributeLanguages;

}
