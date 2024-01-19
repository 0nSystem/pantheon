package com.onsystem.wscapp.pantheon.input.api.dto.applications.application;

import com.onsystem.wscapp.pantheon.input.api.dto.Constants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationWithLanguagesDTO {

    @NotNull(message = Constants.ErrorValidationMessages.NOT_NULL)
    private CreateApplicationDTO application;

    private Set<CreateApplicationLanguageDTO> applicationLanguages;
}
