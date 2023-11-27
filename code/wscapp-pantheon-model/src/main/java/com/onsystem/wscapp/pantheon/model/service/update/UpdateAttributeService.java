package com.onsystem.wscapp.pantheon.model.service.update;

import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdateAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UpdateAttributeService implements IUpdateAttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;

    @Override
    public void updateAttribute(Collection<UpdateAttributeDTO> updateAttribute) {
        updateAttribute.forEach(attributeRepository::update);
    }

    @Override
    public void updateAttributeLanguages(Collection<UpdateAttributeLanguageDTO> updateAttributeLanguage) {
        updateAttributeLanguage.forEach(attributeLanguageRepository::update);
    }
}
