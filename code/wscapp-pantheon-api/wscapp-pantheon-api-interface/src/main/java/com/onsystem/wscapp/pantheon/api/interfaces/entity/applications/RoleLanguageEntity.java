package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;

import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.spublic.LanguageEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_ROLE_LANGUAGE;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = TABLE_ROLE_LANGUAGE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(RoleLanguageKeyEntity.class)
public class RoleLanguageEntity {

    @NotEmpty
    @Max(100)
    private String name;
    @NotEmpty
    @Max(255)
    private String description;

    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    private RoleEntity role;

    @Id
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLanguage")
    private LanguageEntity language;
}
