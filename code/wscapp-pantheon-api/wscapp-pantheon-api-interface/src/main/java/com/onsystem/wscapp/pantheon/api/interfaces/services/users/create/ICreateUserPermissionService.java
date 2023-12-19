package com.onsystem.wscapp.pantheon.api.interfaces.services.users.create;

import java.util.List;

public interface ICreateUserPermissionService {


    void assignPermissionInUser(final List<Integer> permissionIds, final int permissionId);



}
