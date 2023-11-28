package com.onsystem.wscapp.pantheon.model.service.delete;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.delete.IDeletePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DeletePermissionService implements IDeletePermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionLanguageRepository permissionLanguageRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public void deletePermission(Collection<Integer> permissionIds) {
        permissionLanguageRepository.deleteByIdPermissionIn(permissionIds);
        rolePermissionRepository.deleteByIdPermissionIn(permissionIds);

        permissionRepository.deleteAllById(permissionIds);
    }

    @Override
    public void deletePermissionLanguage(int permissionId, Collection<Integer> languageIds) {
        permissionLanguageRepository.deleteByIdPermissionAndIdLanguageIn(permissionId,languageIds);
    }
}
