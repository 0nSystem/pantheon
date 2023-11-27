package com.onsystem.wscapp.pantheon.api.interfaces.services.update;

import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdateRoleService {
    @Transactional
    void updateRoles(final Collection<UpdateRoleDTO> updateRoles);

    @Transactional
    void updateRoleLanguages(final Collection<UpdateRoleLanguageDTO> updateRoleLanguages);

}
