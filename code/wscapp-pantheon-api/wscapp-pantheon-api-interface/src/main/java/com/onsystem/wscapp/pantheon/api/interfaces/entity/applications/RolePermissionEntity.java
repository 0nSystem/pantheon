package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;

import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
    @Id
    @OneToOne
    @JoinColumn(name = "id_permission")
    private PermissionEntity permission;


}
