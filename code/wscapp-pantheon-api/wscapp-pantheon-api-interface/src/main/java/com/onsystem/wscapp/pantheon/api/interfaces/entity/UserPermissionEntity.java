package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "user_permission")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserPermissionKeyEntity.class)
public class UserPermissionEntity {
    @Id
    private Integer idUser;
    @Id
    private Integer idPermission;
}
