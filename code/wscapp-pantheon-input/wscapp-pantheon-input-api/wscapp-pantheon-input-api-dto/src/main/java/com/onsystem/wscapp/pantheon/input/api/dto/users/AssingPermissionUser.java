package com.onsystem.wscapp.pantheon.input.api.dto.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssingPermissionUser {
    @Positive
    private Integer userId;
    @NotEmpty
    @Positive
    private Set<Integer> permissionIds;


}
