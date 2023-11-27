package com.onsystem.wscapp.pantheon.api.interfaces.services.update;

import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdateAttributeService {

    @Transactional
    void updateAttribute(final Collection<UpdateAttributeDTO> updateAttribute);

    @Transactional
    void updateAttributeLanguages(final Collection<UpdateAttributeLanguageDTO> updateAttributeLanguage);

}
