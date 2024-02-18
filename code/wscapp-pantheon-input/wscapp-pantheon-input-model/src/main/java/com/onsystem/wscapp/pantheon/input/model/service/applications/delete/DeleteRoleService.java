package com.onsystem.wscapp.pantheon.input.model.service.applications.delete;

import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RolePermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.delete.IDeleteRoleService;
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
        roleLanguageRepository.deleteByIdRoleIn(roleIds);
        rolePermissionRepository.deleteByIdRoleIn(roleIds);

        roleRepository.deleteAllById(roleIds);
    }

    @Override
    public void deleteRoleLanguage(int roleId, Collection<Integer> languageIds) {
        roleLanguageRepository.deleteByRoleIdRoleAndLanguageIdLanguageIn(roleId, languageIds);
    }
}
