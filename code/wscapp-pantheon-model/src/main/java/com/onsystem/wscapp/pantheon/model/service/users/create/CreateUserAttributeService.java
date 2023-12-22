package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateAttributeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateUserAttributeService implements ICreateAttributeService {


    @Override
    public Set<AttributeDTO> createAttributes(int applicationId, Set<CreateAttributeDTO> createAttribute) {
        return null;
    }

    @Override
    public Set<AttributeWithLanguagesDTO> createAttributesWithLanguages(int applicationId, Set<CreateAttributeWithLanguageDTO> createAttribute) {
        return null;
    }

    @Override
    public Set<AttributeLanguageDTO> createAttributesLanguages(int attributeId, Set<CreateAttributeLanguageDTO> createAttribute) {
        return null;
    }



}
