package com.onsystem.wscapp.pantheon.commons.entity.applications;

import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import com.onsystem.wscapp.pantheon.commons.Constants.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = Constants.TABLE_ATTRIBUTE)
@Builder
@Getter
@Setter
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
    @JoinColumn(name = "id_application")
    private ApplicationEntity application;


    @ToString.Exclude
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
    private Set<AttributeLanguageEntity> attributeLanguages;

    @OneToMany(mappedBy = "attribute")
    private Set<UserAttributeEntity> userAttribute;


}
