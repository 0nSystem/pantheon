package com.onsystem.wscapp.pantheon.api.interfaces.services;

import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Validated
public interface IUserRoleService {


    void insert(final Collection<Integer> idsRoles,final Collection<Integer> idsUsers);
    void delete(final Collection<Integer> idsRoles,final Collection<Integer> idsUsers);
}
