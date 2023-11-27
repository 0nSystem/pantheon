package com.onsystem.wscapp.pantheon.api.interfaces.services.update;

import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionLanguageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IUpdatePermissionService {
    @Transactional
    void updatePermission(final Collection<UpdatePermissionDTO> updatePermission);

    @Transactional
    void updatePermissionLanguages(final Collection<UpdatePermissionLanguageDTO> updatePermissionLanguage);


}
