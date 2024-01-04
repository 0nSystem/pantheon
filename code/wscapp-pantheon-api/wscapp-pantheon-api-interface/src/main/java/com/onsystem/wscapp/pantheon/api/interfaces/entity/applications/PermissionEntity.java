package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;


import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.*;

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
    @ManyToOne
    @JoinColumn(name = "id_application")
    private ApplicationEntity application;


    @ToString.Exclude
    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private Set<PermissionLanguageEntity> permissionLanguages;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = Constants.TABLE_ROLE_PERMISSION, schema = SCHEME_APPLICATION,
            joinColumns = @JoinColumn(name = "id_permission"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<RoleEntity> roles;


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = Constants.TABLE_USER_PERMISSION, schema = SCHEME_USERS,
            joinColumns = @JoinColumn(name = "id_permission"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<UserEntity> user;


}
