package com.onsystem.wscapp.pantheon.api.interfaces.services;

import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Validated
public interface IUserApplicationService {

    void insert(final Collection<Integer> idsApplications,final Collection<Integer> idsUsers);
    void delete(final Collection<Integer> idsApplications,final Collection<Integer> idsUsers);
}
