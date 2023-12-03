package com.onsystem.wscapp.pantheon.api.interfaces.services.applications.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IDeleteRoleService {

    @Transactional
    void deleteRole(final @NotEmpty Collection<Integer> roleIds);

    @Transactional
    void deleteRoleLanguage(final @Positive int roleId,
                            final @NotEmpty Collection<Integer> languageIds);
}
