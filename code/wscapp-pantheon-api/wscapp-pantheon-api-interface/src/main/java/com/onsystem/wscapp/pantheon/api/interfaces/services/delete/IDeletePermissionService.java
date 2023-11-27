package com.onsystem.wscapp.pantheon.api.interfaces.services.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IDeletePermissionService {
    @Transactional
    void deletePermission(final @NotEmpty Collection<Integer> permissionIds);

    @Transactional
    void deletePermissionLanguage(final @Positive int permissionId,
                                  final @NotEmpty Collection<Integer> languageIds);
}
