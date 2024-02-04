package com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.UpdatePermissionLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdatePermissionService {
    @Transactional
    void updatePermission(final Collection<UpdatePermissionDTO> updatePermission);

    @Transactional
    void updatePermissionLanguages(final Collection<UpdatePermissionLanguageDTO> updatePermissionLanguage);


}
