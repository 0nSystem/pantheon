package com.onsystem.wscapp.pantheon.api.interfaces.entity;


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
