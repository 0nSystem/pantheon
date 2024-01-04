package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;


import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_USERS;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_USER_PERMISSION;

@Entity
@Table(schema = SCHEME_USERS, name = TABLE_USER_PERMISSION)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserPermissionKeyEntity.class)
public class UserPermissionEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    @Id
    @OneToOne
    @JoinColumn(name = "id_permission")
    private PermissionEntity permission;
}
