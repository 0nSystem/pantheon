package com.onsystem.wscapp.pantheon.commons.entity.users;


import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = Constants.SCHEME_USERS, name = Constants.TABLE_USER_PERMISSION)
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
