package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionId {
    private Integer idRole;
    private Integer idPermission;

}
