package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_USERS;

@Entity
@Table(schema = SCHEME_USERS, name = "user_role")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserRoleKeyEntity.class)
public class UserRoleEntity {

    @Id
    private Integer idUser;
    @Id
    private Integer idRole;
}
