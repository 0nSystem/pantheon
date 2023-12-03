package com.onsystem.wscapp.pantheon.api.dto.applications.attribute;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAttributeWithLanguageDTO {

    private @NotNull CreateAttributeDTO attribute;

    private Set<CreateAttributeLanguageDTO> attributeLanguages;
}
