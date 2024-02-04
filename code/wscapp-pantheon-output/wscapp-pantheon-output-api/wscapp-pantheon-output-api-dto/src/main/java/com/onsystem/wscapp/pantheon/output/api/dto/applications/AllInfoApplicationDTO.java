package com.onsystem.wscapp.pantheon.output.api.dto.applications;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AllInfoApplicationDTO {


    @NotNull
    private ApplicationInfoDTO applicationInfo;

    private List<AttributeInfoDTO> attributesInfo;

    private List<RoleInfoDTO> rolesInfo;

    private List<PermissionInfoDTO> permissionsInfo;

}
