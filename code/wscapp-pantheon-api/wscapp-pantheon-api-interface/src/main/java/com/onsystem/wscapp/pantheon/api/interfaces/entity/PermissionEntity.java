package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "permission")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermission;

    @Positive
    private int idApplication;

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
    @OneToMany(mappedBy = "permission",fetch = FetchType.LAZY)
    private Set<PermissionLanguageEntity> permissionLanguages;

}
