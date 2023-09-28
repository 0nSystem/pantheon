package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AttributeService implements IAttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public List<AttributeEntity> insert(AttributeEntity... attributeEntities) {
        //TODO Validar que el usuario esta minimo en la aplicacion
        return attributeRepository.saveAll(Arrays.asList(attributeEntities));
    }

    @Override
    public List<AttributeEntity> update(AttributeEntity... attributeEntities) {
        //TODO Validar que el usuario esta minimo en la aplicacion
        return attributeRepository.saveAll(Arrays.asList(attributeEntities));
    }

    @Override
    public List<AttributeEntity> findByApplicationId(int applicationId) {
        //TODO Validar que el usuario esta minimo en la aplicacion
        return attributeRepository.findByIdApplication(applicationId);
    }
}
