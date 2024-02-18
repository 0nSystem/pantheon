package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.UpdateAttributeLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdateAttributeService {

    @Transactional
    void updateAttribute(final Collection<UpdateAttributeDTO> updateAttribute);

    @Transactional
    void updateAttributeLanguages(final Collection<UpdateAttributeLanguageDTO> updateAttributeLanguage);

}
