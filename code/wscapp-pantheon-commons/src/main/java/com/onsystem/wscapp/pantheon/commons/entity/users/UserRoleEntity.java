package com.onsystem.wscapp.pantheon.commons.entity.users;

import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import com.onsystem.wscapp.pantheon.commons.Constants.*;

@Entity
@Table(schema = Constants.SCHEME_USERS, name = Constants.TABLE_USER_ROLE)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserRoleKeyEntity.class)
public class UserRoleEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    @Id
    @OneToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
}
