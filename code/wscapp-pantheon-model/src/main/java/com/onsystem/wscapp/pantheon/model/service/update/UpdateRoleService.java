package com.onsystem.wscapp.pantheon.model.service.update;

import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.update.IUpdateRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UpdateRoleService implements IUpdateRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleLanguageRepository roleLanguageRepository;

    @Override
    public void updateRoles(Collection<UpdateRoleDTO> updateRoles) {
        updateRoles.forEach(roleRepository::update);
    }

    @Override
    public void updateRoleLanguages(Collection<UpdateRoleLanguageDTO> updateRoleLanguages) {
        updateRoleLanguages.forEach(roleLanguageRepository::update);
    }
}
