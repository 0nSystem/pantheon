package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionKeyEntity {
    private Integer idRole;
    private Integer idPermission;

}
