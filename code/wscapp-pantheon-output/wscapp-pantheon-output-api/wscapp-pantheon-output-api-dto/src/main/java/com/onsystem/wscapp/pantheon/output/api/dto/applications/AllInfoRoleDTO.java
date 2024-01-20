package com.onsystem.wscapp.pantheon.output.api.dto.applications;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class AllInfoRoleDTO extends RoleInfoDTO {


    private List<PermissionInfoDTO> permissionInfo;

}
