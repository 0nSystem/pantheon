package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IRoleService {

    List<RoleEntity> insert(RoleEntity... roleEntities);
    List<RoleEntity> update(RoleEntity... roleEntities);

    List<RoleEntity> findByApplicationId(final int applicationId);

}
