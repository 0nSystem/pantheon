package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;


import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_PERMISSION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_PERMISSION)
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
    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private Set<PermissionLanguageEntity> permissionLanguages;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = Constants.TABLE_ROLE_PERMISSION, schema = SCHEME_APPLICATION,
            joinColumns = @JoinColumn(name = "idPermission"),
            inverseJoinColumns = @JoinColumn(name = "idRole")
    )
    private Set<RoleEntity> roles;


}
