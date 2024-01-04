package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.*;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_ATTRIBUTE)
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
