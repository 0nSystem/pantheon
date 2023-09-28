package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IAttributeService {

    List<AttributeEntity> insert(AttributeEntity... attributeEntities);
    List<AttributeEntity> update(AttributeEntity... attributeEntities);

    List<AttributeEntity> findByApplicationId(final int applicationId);
}
