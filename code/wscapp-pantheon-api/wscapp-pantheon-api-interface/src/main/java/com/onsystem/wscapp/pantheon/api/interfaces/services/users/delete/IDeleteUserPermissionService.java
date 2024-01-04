package com.onsystem.wscapp.pantheon.api.interfaces.services.users.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface IDeleteUserPermissionService {


    void removePermissionAssigned(final @NotEmpty List<Integer> permissionIds,
                                  final @Positive int userId);
}
