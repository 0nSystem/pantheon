package com.onsystem.wscapp.pantheon.output.api.dto.users;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.PermissionInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.RoleInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataInApplicationDTO {

    private UserInfoDTO userInfo;
    private Set<PermissionInfoDTO> permissions;
    private Set<RoleInfoDTO> role;
    private Set<AttributeUserDataDTO> attribute;


}
