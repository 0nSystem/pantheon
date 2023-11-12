package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = "role_language")
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
