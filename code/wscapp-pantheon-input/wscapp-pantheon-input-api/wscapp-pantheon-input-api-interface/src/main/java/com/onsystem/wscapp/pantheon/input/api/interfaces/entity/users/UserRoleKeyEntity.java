package com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users;

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
    private Integer role;
    @NotNull
    private Integer user;
}
