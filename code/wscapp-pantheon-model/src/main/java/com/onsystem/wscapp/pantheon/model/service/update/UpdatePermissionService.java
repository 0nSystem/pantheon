package com.onsystem.wscapp.pantheon.model.service.update;

import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdatePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UpdatePermissionService implements IUpdatePermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionLanguageRepository permissionLanguageRepository;


    @Override
    public void updatePermission(Collection<UpdatePermissionDTO> updatePermission) {
        updatePermission.forEach(permissionRepository::update);
    }

    @Override
    public void updatePermissionLanguages(Collection<UpdatePermissionLanguageDTO> updatePermissionLanguage) {
        updatePermissionLanguage.forEach(permissionLanguageRepository::update);
    }
}
