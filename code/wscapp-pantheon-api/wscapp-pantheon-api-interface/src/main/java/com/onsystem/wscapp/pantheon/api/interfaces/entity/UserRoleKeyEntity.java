package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleKeyEntity {
    @NotNull
    private Integer idRole;
    @NotNull
    private Integer idUser;
}
