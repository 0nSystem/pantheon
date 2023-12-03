package com.onsystem.wscapp.pantheon.model.service.applications.delete;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.delete.IDeleteAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DeleteAttributeService implements IDeleteAttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;

    @Override
    public void deleteAttribute(Collection<Integer> attributeIds) {
        attributeLanguageRepository.deleteByAttributeIdAttributeIn(attributeIds);

        attributeRepository.deleteAllById(attributeIds);
    }

    @Override
    public void deleteAttributeLanguage(int attributeId, Collection<Integer> languageIds) {
        attributeLanguageRepository.deleteByIdAttributeAndIdLanguageIn(attributeId, languageIds);
    }
}
