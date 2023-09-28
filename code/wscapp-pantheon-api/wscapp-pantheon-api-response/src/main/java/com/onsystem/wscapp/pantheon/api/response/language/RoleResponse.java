package com.onsystem.wscapp.pantheon.api.response.language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleResponse {


    private int idRole;

    private int idApplication;

    private String name;

    private String description;
}
