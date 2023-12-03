package com.onsystem.wscapp.pantheon.model.service.applications.update;

import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.UpdateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.update.IUpdateAttributeService;
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
