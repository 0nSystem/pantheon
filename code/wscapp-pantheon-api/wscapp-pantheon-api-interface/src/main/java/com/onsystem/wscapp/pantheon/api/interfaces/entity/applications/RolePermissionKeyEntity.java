package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionKeyEntity {
    private Integer role;
    private Integer permission;

}
