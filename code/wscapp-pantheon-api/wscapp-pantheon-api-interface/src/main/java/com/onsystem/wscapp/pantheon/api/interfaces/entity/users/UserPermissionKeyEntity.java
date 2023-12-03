package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionKeyEntity {
    private Integer idUser;
    private Integer idPermission;
}
