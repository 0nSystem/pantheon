package com.onsystem.wscapp.pantheon.model.service.delete;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.delete.IDeleteAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class DeleteAttributeService implements IDeleteAttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeLanguageRepository attributeLanguageRepository;

    @Override
    public void deleteAttribute(Collection<Integer> attributeIds) {
    }

    @Override
    public void deleteAttributeLanguage(int attributeId, Collection<Integer> languageIds) {
    }
}
