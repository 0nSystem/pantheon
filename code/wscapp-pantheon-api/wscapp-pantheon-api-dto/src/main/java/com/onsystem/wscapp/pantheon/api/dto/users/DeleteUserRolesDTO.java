package com.onsystem.wscapp.pantheon.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeleteUserRolesDTO {

    private int userId;
    private Set<Integer> roleIds;

}
