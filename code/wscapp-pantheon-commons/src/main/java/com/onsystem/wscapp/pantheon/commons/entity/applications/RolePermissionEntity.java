package com.onsystem.wscapp.pantheon.commons.entity.applications;

import com.onsystem.wscapp.pantheon.commons.Constants;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = Constants.TABLE_ROLE_PERMISSION)
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
