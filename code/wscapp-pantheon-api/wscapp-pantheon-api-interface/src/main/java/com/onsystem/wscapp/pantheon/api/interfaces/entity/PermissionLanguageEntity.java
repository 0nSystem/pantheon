package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = Constants.TABLE_PERMISSION_LANGUAGE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(PermissionLanguageKeyEntity.class)
public class PermissionLanguageEntity {


    @NotEmpty
    @Max(100)
    private String name;

    @NotEmpty
    @Max(255)
    private String description;

    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPermission")
    private PermissionEntity permission;


    @Id
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLanguage")
    private LanguageEntity language;


}
