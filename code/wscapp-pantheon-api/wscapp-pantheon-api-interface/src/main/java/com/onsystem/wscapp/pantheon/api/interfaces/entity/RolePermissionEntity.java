package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_ROLE_PERMISSION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_ROLE_PERMISSION)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RolePermissionKeyEntity.class)
public class RolePermissionEntity {

    @Id
    private Integer idRole;
    @Id
    private Integer idPermission;


}
