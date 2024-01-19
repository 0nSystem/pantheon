package com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users;

import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.Constants.*;

@Entity
@Table(schema = SCHEME_USERS, name = TABLE_USER_ROLE)
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
