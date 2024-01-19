package com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionKeyEntity {
    private Integer user;
    private Integer permission;
}
