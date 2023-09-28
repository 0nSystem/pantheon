package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId {

    private Integer idRole;
    private Integer idUser;
}
