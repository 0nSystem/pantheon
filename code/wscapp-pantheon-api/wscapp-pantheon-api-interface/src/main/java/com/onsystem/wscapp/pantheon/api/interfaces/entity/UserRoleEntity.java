package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "user_role")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserRoleId.class)
public class UserRoleEntity {

    @Id
    private Integer idUser;
    @Id
    private Integer idRole;
}
