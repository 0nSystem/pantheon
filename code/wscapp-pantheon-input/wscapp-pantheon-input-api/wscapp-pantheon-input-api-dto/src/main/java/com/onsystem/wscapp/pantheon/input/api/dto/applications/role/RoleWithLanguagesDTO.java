package com.onsystem.wscapp.pantheon.input.api.dto.applications.role;

import com.onsystem.wscapp.pantheon.input.api.dto.Constants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleWithLanguagesDTO {

    @NotNull(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    public RoleDTO role;

    private Set<RoleLanguageDTO> languagesRole;
}
