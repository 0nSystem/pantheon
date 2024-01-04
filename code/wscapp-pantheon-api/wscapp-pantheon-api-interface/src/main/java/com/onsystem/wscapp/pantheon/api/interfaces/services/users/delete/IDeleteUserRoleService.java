package com.onsystem.wscapp.pantheon.api.interfaces.services.users.delete;

import com.onsystem.wscapp.pantheon.api.dto.users.DeleteUserRolesDTO;

import java.util.Set;

public interface IDeleteUserRoleService {

    void deleteRoles(Set<DeleteUserRolesDTO> deleteUserRoles);
}
