package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IValidationReferenceToApplicationService;
import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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
