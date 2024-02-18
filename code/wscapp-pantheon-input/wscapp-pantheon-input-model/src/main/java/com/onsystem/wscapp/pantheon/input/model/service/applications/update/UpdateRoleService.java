package com.onsystem.wscapp.pantheon.input.model.service.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update.IUpdateRoleService;
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
