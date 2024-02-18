package com.onsystem.wscapp.pantheon.commons.entity.users;

import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.dto.HightAuditFields;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.commons.dto.DeleteAuditFields;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = Constants.SCHEME_USERS, name = Constants.TABLE_USER,
        uniqueConstraints = @UniqueConstraint(columnNames = {"email", "login"}))
public class UserEntity implements HightAuditFields, DeleteAuditFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotEmpty
    @Max(50)
    private String name;

    @NotEmpty
    @Max(50)
    private String surname;

    @NotEmpty
    @Max(100)
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Max(30)
    @Column(unique = true)
    private String login;

    @NotEmpty
    @Max(255)
    private String password;

    @NotNull
    private Timestamp highDate;

    @Nullable
    @Positive
    private Integer highIdUser;


    @Nullable
    private Timestamp deleteDate;
    @Nullable
    private Integer deleteIdUser;

    @ManyToMany
    @JoinTable(schema = Constants.SCHEME_USERS, name = Constants.TABLE_USER_PERMISSION,
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_permission")
    )
    private Set<PermissionEntity> permission;

    @OneToMany
    @JoinTable(name = Constants.TABLE_USER_ROLE, schema = Constants.SCHEME_USERS,
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<RoleEntity> role;


    @OneToMany(mappedBy = "user")
    private List<UserAttributeEntity> userAttribute;
}
