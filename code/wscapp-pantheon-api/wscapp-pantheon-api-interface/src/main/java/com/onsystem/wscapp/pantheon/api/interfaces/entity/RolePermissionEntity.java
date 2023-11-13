package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "role_permission")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RolePermissionId.class)
public class RolePermissionEntity {

    @Id
    private Integer idRole;
    @Id
    private Integer idPermission;
}
