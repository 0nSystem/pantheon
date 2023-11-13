package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Id
    private Integer idRole;
    @NotNull
    @Id
    private Integer idLanguage;


    @NotEmpty
    @Max(100)
    private String name;
    @NotEmpty
    @Max(255)
    private String description;
}
