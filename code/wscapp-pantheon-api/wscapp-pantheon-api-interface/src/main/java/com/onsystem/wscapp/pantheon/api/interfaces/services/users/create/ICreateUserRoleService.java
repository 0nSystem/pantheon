package com.onsystem.wscapp.pantheon.api.interfaces.services.users.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public interface ICreateUserRoleService {

    void assignRole(final @NotEmpty Set<Integer> roleIds,
                    final @Positive int userId);
}
