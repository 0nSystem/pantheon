package com.onsystem.wscapp.pantheon.api.dto.users;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserAndAssignMetadataApplicationsDTO {

    @NotNull
    private CreateUserDTO user;


    private List<Integer> applicationIds;

    private List<Integer> permissionIds;

    private List<Integer> roleIds;

    private List<CreateUserAttributeDTO> attributes;




}
