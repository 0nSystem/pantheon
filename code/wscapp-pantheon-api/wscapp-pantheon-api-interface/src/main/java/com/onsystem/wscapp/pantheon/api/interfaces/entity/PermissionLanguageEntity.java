package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = "permission_language")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PermissionLanguageEntity {


    @EmbeddedId
    private PermissionLanguageKeyEntity permissionLanguageKeyEntity;

    @NotEmpty
    @Max(100)
    private String name;

    @NotEmpty
    @Max(255)
    private String description;

}
