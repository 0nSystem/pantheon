package com.onsystem.wscapp.pantheon.commons.entity.applications;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleLanguageKeyEntity {

    @NotNull
    private Integer role;
    @NotNull
    private Integer language;
}
