package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;


import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.*;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_ROLE)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;


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
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<RoleLanguageEntity> roleLanguages;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<PermissionEntity> permissions;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_USER_ROLE, schema = SCHEME_USERS,
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<UserEntity> user;


}
