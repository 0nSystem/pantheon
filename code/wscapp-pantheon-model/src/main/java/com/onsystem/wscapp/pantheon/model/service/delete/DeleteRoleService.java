package com.onsystem.wscapp.pantheon.model.service.delete;

import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleLanguageRepository;
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

    @Override
    public void deleteRole(Collection<Integer> roleIds) {
        roleRepository.deleteAllByIdInBatch(roleIds);
    }

    @Override
    public void deleteRoleLanguage(int roleId, Collection<Integer> languageIds) {

    }
}
