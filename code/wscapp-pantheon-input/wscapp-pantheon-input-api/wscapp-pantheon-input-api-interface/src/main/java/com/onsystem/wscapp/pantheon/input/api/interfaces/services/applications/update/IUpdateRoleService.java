package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.UpdateRoleLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdateRoleService {
    @Transactional
    void updateRoles(final Collection<UpdateRoleDTO> updateRoles);

    @Transactional
    void updateRoleLanguages(final Collection<UpdateRoleLanguageDTO> updateRoleLanguages);

}
