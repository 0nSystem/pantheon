package com.onsystem.wscapp.pantheon.input.model.service.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.UpdatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update.IUpdatePermissionService;
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
