package com.onsystem.wscapp.pantheon.input.model.service;

import com.onsystem.wscapp.pantheon.input.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.IValidationReferenceToApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationReferenceToApplicationService implements IValidationReferenceToApplicationService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void validationReferenceRolePermissionBelongApplication(int idRole, Set<Integer> idsPermissions) throws InfoException {
        throw new InfoException("Not implemented");
    }
}
