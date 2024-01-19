package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete;


import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserRolesDTO;

import java.util.Set;

public interface IDeleteUserRoleService {

    void deleteRoles(Set<DeleteUserRolesDTO> deleteUserRoles);
}
