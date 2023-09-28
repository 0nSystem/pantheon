package com.onsystem.wscapp.pantheon.api.interfaces.services;

import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Validated
public interface IRolePermissionService {

    void insert(final Collection<Integer> idsRoles, final Collection<Integer> idsPermissions);
    void delete(final Collection<Integer> idsRoles, final Collection<Integer> idsPermissions);
}
