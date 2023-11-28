package com.onsystem.wscapp.pantheon.model.service.delete;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.delete.IDeleteRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DeleteRoleService implements IDeleteRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleLanguageRepository roleLanguageRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public void deleteRole(Collection<Integer> roleIds) {
        roleLanguageRepository.deleteByRoleIdRoleIn(roleIds);
        rolePermissionRepository.deleteByIdRoleIn(roleIds);

        roleRepository.deleteAllById(roleIds);
    }

    @Override
    public void deleteRoleLanguage(int roleId, Collection<Integer> languageIds) {
        roleLanguageRepository.deleteByRoleIdRoleAndLanguageIdLanguageIn(roleId, languageIds);
    }
}
