package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionEntity> create(PermissionEntity... permissionEntities) {
        //TODO usuario relacionado con la aplicacion
        return permissionRepository.saveAll(Arrays.asList(permissionEntities));
    }

    @Override
    public List<PermissionEntity> update(PermissionEntity... permissionEntities) {
        //TODO usuario relacionado con la aplicacion
        return permissionRepository.saveAll(Arrays.asList(permissionEntities));
    }

    @Override
    public List<PermissionEntity> findByApplicationId(int applicationId) {
        //TODO usuario relacionado con la aplicacion
        return permissionRepository.findByIdApplication(applicationId);
    }
}
