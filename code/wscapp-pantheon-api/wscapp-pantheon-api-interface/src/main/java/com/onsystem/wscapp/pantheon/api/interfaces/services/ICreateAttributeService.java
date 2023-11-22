package com.onsystem.wscapp.pantheon.api.interfaces.services;


import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Collection;
import java.util.Set;

public interface ICreateAttributeService {

    @NotNull Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final @Positive int applicationId,
                                                                          final @NotNull @NotEmpty Collection<CreateAttributeDTO> createAttribute);

    @NotNull Set<AttributeLanguageDTO> createAttributesLanguages(final int attributeId,
                                                                 final @NotNull @NotEmpty Collection<CreateAttributeLanguageDTO> createAttribute);

}
