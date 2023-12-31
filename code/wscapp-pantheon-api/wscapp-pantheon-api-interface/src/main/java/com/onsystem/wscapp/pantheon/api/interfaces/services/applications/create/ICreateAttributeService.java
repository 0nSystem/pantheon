package com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create;


import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.AttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.AttributeWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.CreateAttributeWithLanguageDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public interface ICreateAttributeService {

    @NotNull Set<AttributeDTO> createAttributes(final @Positive int applicationId,
                                                final @NotNull @NotEmpty Set<CreateAttributeDTO> createAttribute);

    @NotNull Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(final @Positive int applicationId,
                                                                          final @NotNull @NotEmpty Set<CreateAttributeWithLanguageDTO> createAttribute);

    @NotNull Set<AttributeLanguageDTO> createAttributesLanguages(final int attributeId,
                                                                 final @NotNull @NotEmpty Set<CreateAttributeLanguageDTO> createAttribute);

}
