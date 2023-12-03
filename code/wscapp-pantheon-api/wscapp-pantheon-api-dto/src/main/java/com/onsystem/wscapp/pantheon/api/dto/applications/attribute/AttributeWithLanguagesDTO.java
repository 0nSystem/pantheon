package com.onsystem.wscapp.pantheon.api.dto.applications.attribute;


import com.onsystem.wscapp.pantheon.api.dto.Constants;
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
public class AttributeWithLanguagesDTO {

    @NotNull(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    private AttributeDTO attribute;

    private Set<AttributeLanguageDTO> attributeLanguages;
}
