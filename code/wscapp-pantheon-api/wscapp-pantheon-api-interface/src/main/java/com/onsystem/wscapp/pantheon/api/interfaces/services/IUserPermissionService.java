package com.onsystem.wscapp.pantheon.api.interfaces.services;

import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Validated
public interface IUserPermissionService {

    void insert(final Collection<Integer> idsPermissions,final Collection<Integer> idsUsers);
    void delete(final Collection<Integer> idsPermissions,final Collection<Integer> idsUsers);
}
