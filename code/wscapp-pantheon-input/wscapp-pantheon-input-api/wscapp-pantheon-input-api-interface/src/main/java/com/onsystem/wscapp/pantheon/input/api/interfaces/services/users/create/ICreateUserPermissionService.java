package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create;

import java.util.Set;

public interface ICreateUserPermissionService {


    void assignPermissionInUser(final Set<Integer> permissionIds, final int userId);



}
