package com.onsystem.wscapp.pantheon.api.interfaces.services;


import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IPermissionService {

    List<PermissionEntity> create(PermissionEntity... permissionEntities);
    List<PermissionEntity> update(PermissionEntity... permissionEntities);
    List<PermissionEntity> findByApplicationId(final int applicationId);
}
